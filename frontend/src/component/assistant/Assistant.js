import React, { useEffect } from "react";
import SpeechRecognition, { useSpeechRecognition } from "react-speech-recognition/lib/SpeechRecognition";
import VoiceToaster from "../toaster/VoiceToaster";

function Assistant() {
  const {
    transcript,
    listening,
    browserSupportsSpeechRecognition
  } = useSpeechRecognition();

  useEffect(() => {
    console.log(browserSupportsSpeechRecognition);

    if (!browserSupportsSpeechRecognition) {
      alert("Your Browser doesn't support Speech To Text");
    }
  }, []);

  return (
    <div>
      {listening && <VoiceToaster input={transcript} listening={listening} />}

      <button className="px-3.5 py-2 fixed bottom-10 right-10 rounded-full shadow shadow-slate-600 bg-white" onClick={SpeechRecognition.startListening}>
        <i className="fa-solid fa-microphone text-2xl text-blue-800"></i>
      </button>
    </div>
  );
}

export default Assistant;
