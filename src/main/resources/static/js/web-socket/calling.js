var stompClient = null;

const connectId = '#connect';
const disconnectId = '#disconnect';
const conversationDivId = '#conversationDiv';
const myStateLineId = '#myStateLine'

const waitTimeSecondsId = '#centralTelephoneWaitTimeSeconds' 

var lineState = {
  lineId: $(myTelephoneLineElementId).val(),
  available: true,
  talkingWithLineId: null,
  callState: null,
  callLogId: null
}

const lineStatesList = {
  solicitada: 'Solicitada',
  recibida: 'Recibida',
  inalcanzable: 'Inalcanzable',
  finalizada: 'Finalizada',
  cancelada: 'Cancelada',
  rechazada: 'Rechazada',
  noDefinido: 'No definido'
}

const actionsList = {
  recibir: 'Recibir',
  rechazar: 'Rechazar',
  cancelar: 'Cancelar',
  finalizar: 'Finalizar'
}

function statesHandler(incomingMessage) {
  const {callLogId, state, from } = incomingMessage;
  const {
    solicitada,
    recibida,
    inalcanzable,
    finalizada,
    cancelada,
    rechazada,
    noDefinido,
  } = lineStatesList;

  switch (state) {
    case solicitada:
      if (lineState.available) {
        const { receiveBtnId, rejectBtnId } = actionButtonsIds;
        const labelContent = 'Llamada entrante...';
        const bodyContent = `Llamada de ${from}`;

        updateLineState(callLogId, false, from, solicitada);
        updateModalContent(labelContent, bodyContent, [receiveBtnId, rejectBtnId]);
        showCallModal()
      }
      break;

    case recibida:
      const { talkingWithLineId } = lineState;
      const { endBtnId } = actionButtonsIds;
      const labelContent = 'Llamada en curso...';
      const bodyContent = `En llamada con ${talkingWithLineId}`;

      restartCountUp();
      updateLineState(callLogId, false, talkingWithLineId, recibida);
      updateModalContent(labelContent, bodyContent, [endBtnId]);
      break;

    case inalcanzable:
      break;

    case finalizada:
      stopCountUp();
      updateLineState(null, true, null, finalizada);
      closeCallModal();
      showNotification(finalizada, 'success', formatDuration());
      break;

    case cancelada:
      stopCountUp();
      updateLineState(null, true, null, cancelada);
      closeCallModal();
      showNotification(cancelada, 'info', formatDuration());
      break;

    case rechazada:
      stopCountUp();
      updateLineState(null, true, null, rechazada);
      closeCallModal();
      showNotification(rechazada, 'info', );
      break;

    case noDefinido:
      stopCountUp();
      updateLineState(null, true, null, noDefinido);
      closeCallModal();
      showNotification(noDefinido, 'error', formatDuration());
      break;

    default:
      break;
  }
}

function handlerButtonActions(typeButton) {
  const { callLogId, lineId, talkingWithLineId } = lineState;
  const {
    recibir,
    rechazar,
    cancelar,
    finalizar
  } = actionsList;

  switch (typeButton) {
    case recibir:
      const { endBtnId } = actionButtonsIds;
      const { recibida } = lineStatesList;
      const labelContent = 'Llamada en curso...';
      const bodyContent = `En llamada con ${talkingWithLineId}`;

      startCountUp();
      updateLineState(callLogId, false, talkingWithLineId, recibida)
      sendMessage(callLogId,lineId, talkingWithLineId, recibida, formatDuration());
      updateModalContent(labelContent, bodyContent, [endBtnId]);
      break;

    case rechazar:
      const { rechazada } = lineStatesList;

      stopCountUp();
      closeCallModal();
      sendMessage(callLogId, lineId, talkingWithLineId, rechazada, formatDuration());
      updateLineState(null, true, null, rechazada)
      showNotification(rechazada, 'error', formatDuration());
      break;

    case cancelar:
      const { cancelada } = lineStatesList;

      stopCountUp();
      closeCallModal();
      sendMessage(callLogId, lineId, talkingWithLineId, cancelada, formatDuration());
      updateLineState(null, true, null, cancelada);
      showNotification(cancelada, 'info', formatDuration());
      break;

    case finalizar:
      const { finalizada } = lineStatesList;

      stopCountUp();
      closeCallModal();
      sendMessage(callLogId, lineId, talkingWithLineId, finalizada, formatDuration());
      updateLineState(null, true, null, finalizada);
      showNotification(finalizada, 'success', formatDuration());
      break;

    default:
      break;
  }
}

function filterIncomingMessage(messageOutput) {
  const message = JSON.parse(messageOutput.body);
  const { solicitada } = lineStatesList
  const { lineId, callState, available, talkingWithLineId } = lineState;
  const { to, callLogId, state, from  } = message;

  if (to == lineId) {
    showMessageOutput(message);
    statesHandler(message);
  }

  if(from == lineId && state == solicitada){
    updateLineState(callLogId, available, talkingWithLineId, callState)
  }
}

function updateLineState(callLogId, available, talkingWithLineId, callState) {

  $(myStateLineId).val(available? 'Disponible':'No Disponible');

  lineState.callLogId = callLogId;
  lineState.available = available;
  lineState.talkingWithLineId = talkingWithLineId;
  lineState.callState = callState;
}

function setConnected(connected) {
  $(myStateLineId).val(connected ? 'Disponible' : 'No Disponible')
  document.getElementById('connect').disabled = connected;
  document.getElementById('disconnect').disabled = !connected;
  document.getElementById('conversationDiv').style.visibility
    = connected ? 'visible' : 'hidden';
  document.getElementById('response').innerHTML = '';
}

function connect() {
  const socket = new SockJS('/call-service');

  stompClient = Stomp.over(socket);

  stompClient.connect({}, function (frame) {
    setConnected(true);

    console.log('Connected: ' + frame);

    stompClient.subscribe('/topic/call-log', function (messageOutput) {
      filterIncomingMessage(messageOutput);
    });
  });
}

function disconnect() {
  if (stompClient != null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
}

function sendMessage(callLogId, myLineId, receiverTelephoneLineId, state, duration) {
  const from = myLineId;
  const to = receiverTelephoneLineId;

  const message = { callLogId, from, to, state, duration };

  stompClient.send('/app/call-service', {}, JSON.stringify(message));
}

function verifyUnreachebleState(){
  const { solicitada, inalcanzable } = lineStatesList;
  const {callLogId, lineId, talkingWithLineId} = lineState;

  if(lineState.callState == solicitada  & applyCounter) {
    sendMessage(callLogId, lineId, talkingWithLineId, inalcanzable, formatDuration());
    updateLineState(null, true, null, inalcanzable);
    closeCallModal();
    showNotification(inalcanzable, 'error', formatDuration());
  }
}

function requestCall(receiverTelephoneLineId) {

  if($(myStateLineId).val() === 'Disponible') {
    const { callLogId, lineId } = lineState;
    const { solicitada } = lineStatesList;
    const { cancelBtnId } = actionButtonsIds;
    const labelContent = 'Llamando...';
    const bodyContent = `Llamando a ${receiverTelephoneLineId}. Espere...`;
  
    updateLineState(callLogId, false, receiverTelephoneLineId, solicitada);
    sendMessage(null, lineId, receiverTelephoneLineId, solicitada, formatDuration());
    updateModalContent(labelContent, bodyContent, [cancelBtnId]);   
    startCountUp();
    showCallModal();

    const customWaitTimeSeconds = $(waitTimeSecondsId).val();
    const waitTime = Number(customWaitTimeSeconds) > 0 ? customWaitTimeSeconds : 30;
  
    // verify state after 30 seconds
    applyCounter = true;
    //setTimeout(verifyUnreachebleState, waitTime * 1000);
  }else{
    toastr.error('Su línea no está disponible en este momento.');
  }
}