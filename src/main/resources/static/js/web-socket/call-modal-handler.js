
const responseId = '#response';

const callModalId = '#callModal';
const callModalLabelId = '#callModalLabel';
const callModalBodyId = '#callModalBody';
const myTelephoneLineElementId = '#myTelephoneLineId';

const actionButtonsIds = {
    receiveBtnId: '#receiveBtn',
    rejectBtnId: '#rejectBtn',
    cancelBtnId: '#cancelBtn',
    endBtnId: '#endBtn',
  }

function updateModalContent(labelContent, bodyContent, activeBtnsIds) {
    const {
      receiveBtnId,
      rejectBtnId,
      cancelBtnId,
      endBtnId,
    } = actionButtonsIds;
  
    const actionBtnIdsArray = [
      receiveBtnId,
      rejectBtnId,
      cancelBtnId,
      endBtnId,
    ]
  
    $(callModalLabelId).text(labelContent);
    $(callModalBodyId).text(bodyContent);
  
    actionBtnIdsArray.forEach((btnId) => $(btnId).hide())
    activeBtnsIds.forEach((btnId) => $(btnId).show())
  }

  function closeCallModal() {
    $(callModalId).modal('hide');
    updateModalContent('', '', []);
  }
  
  function showCallModal() {
    $(callModalId).modal({dialogClass: 'no-close', backdrop: 'static', keyboard: false});
  }

  function showNotification(state, messageType, duration) {
    const message = `Llamada: ${state}. Tiempo (mm:ss): ${duration} `;
    switch (messageType) {
      case 'success':
        toastr.success(message);
        break;
      case 'error':
        toastr.error(message);
        break;
      case 'info':
      default:
        toastr.info(message);
        break;
    }
  }

  function showMessageOutput(messageOutput) {
    const { callLogId, from, to, state, timeStamp } = messageOutput;
    const messageLog = `${callLogId} | ${from}->${to}:${state} (${timeStamp})`;
  
    const response = $(responseId);
    const p = document.createElement('p');
  
    p.appendChild(document.createTextNode(messageLog));
  
    response.append(p);
  }