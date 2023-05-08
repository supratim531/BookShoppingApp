import React, { useEffect, useState } from "react";
import SpeechRecognition, { useSpeechRecognition } from "react-speech-recognition/lib/SpeechRecognition";
import VoiceToaster from "../toaster/VoiceToaster";
import sound from "../../assets/accept.mp3";
import { useNavigate } from "react-router-dom";
import { recommenderAxios } from "../../axios/axios";
import useSpeechSynthesis from "react-speech-kit/dist/useSpeechSynthesis";

function Assistant() {
  const { speak } = useSpeechSynthesis();
  const {
    transcript,
    listening,
    browserSupportsSpeechRecognition
  } = useSpeechRecognition();
  const navigate = useNavigate();
  const [voiceOver, setVoiceOver] = useState(true);

  const takeAction = (query, result) => {
    if (result === "harry-potter") {
      const responses = [
        "Here it is, check this once.",
        "I have got this one for you.",
        "Here is a book of Harry Potter.",
        "I have found this, hope you will like it.",
        "I have tried to find some of Harry Potter."
      ];
      const random = Math.floor(Math.random() * responses.length);

      navigate("/book/Harry-Potter-and-the-Sorcerer's-Stone-(Book-1)?bookId=BWBK17601");

      if (query.match("rowling")) {
        speak({ text: responses[3] });
      } else {
        speak({ text: responses[random] });
      }
    } else if (result === "the-dark-tower") {
      navigate("/book/The-Drawing-of-the-Three-(The-Dark-Tower,-Book-2)?bookId=BWBK17602");
    }
  }

  const fetchAssistantAnswer = async query => {
    try {
      const res = await recommenderAxios.get(`/assistant-response?query=${query}`);
      console.log("res:", res);
      const result = res.data.data;
      setVoiceOver(true);
      takeAction(query, result);
    } catch (err) {
      console.log("err:", err);
      setVoiceOver(true);
    }
  }

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
      fetchAssistantAnswer(transcript);
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
