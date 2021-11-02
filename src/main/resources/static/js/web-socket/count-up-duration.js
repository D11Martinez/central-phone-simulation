let countUpInterval = null;
let totalSeconds = 0;

const minutesLabelId = "#minutes";
const secondsLabelId = "#seconds";

// inverval count up //
function startCountUp() {
    totalSeconds = 0;
    countUpInterval = setInterval(setTime, 1000);
  }
  
  function setTime() {
    ++totalSeconds;
  
    $(secondsLabelId).html(pad(totalSeconds%60));
    $(minutesLabelId).html(pad(parseInt(totalSeconds/60)));
  }
  
  function formatDuration(){
    const seconds = pad(totalSeconds%60);
    const minutes = pad(parseInt(totalSeconds/60));
    return `${minutes}:${seconds}`
  }
  
  function pad(val)
  {
      var valString = val + "";
      if(valString.length < 2)
      {
          return "0" + valString;
      }
      else
      {
          return valString;
      }
  }
  
  function restartCountUp(){
    totalSeconds = 0;
  }
  
  function stopCountUp() {
    clearInterval(countUpInterval);
  }