import React, { useEffect, useState } from "react";
import SpeechRecognition, { useSpeechRecognition } from "react-speech-recognition/lib/SpeechRecognition";
import VoiceToaster from "../toaster/VoiceToaster";
import sound from "../../assets/accept.mp3";
import { useNavigate } from "react-router-dom";

function Assistant() {
  const {
    transcript,
    listening,
    browserSupportsSpeechRecognition
  } = useSpeechRecognition();
  const navigate = useNavigate();
  const [voiceOver, setVoiceOver] = useState(true);

  useEffect(() => {
    console.log("Mic supported:", browserSupportsSpeechRecognition);

    if (!browserSupportsSpeechRecognition) {
      alert("Your Browser doesn't support Speech To Text");
    }
  }, []);

  useEffect(() => {
    if (!listening && !voiceOver) {
      const acceptanceSound = new Audio(sound);
      acceptanceSound.play();
      setVoiceOver(true);
      setTimeout(() => {
        if (transcript.toLowerCase() === "harry potter") {
          navigate("/book/Harry-Potter-and-the-Sorcerer's-Stone-(Book-1)?bookId=BWBK17601");
        } else {
          alert("Do not understand");
        }
      }, 400);
    }
  }, [listening]);

  return (
    <div>
      {
        listening &&
        <VoiceToaster input={transcript} listening={listening} />
      }

      <button className="px-3.5 py-2 fixed bottom-10 right-10 rounded-full shadow shadow-slate-600 bg-white" onClick={() => {
        setVoiceOver(false);
        SpeechRecognition.startListening();
      }}>
        <i className="fa-solid fa-microphone text-2xl text-blue-800"></i>
      </button>
    </div>
  );
}

export default Assistant;
