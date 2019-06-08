package tk.burdukowsky.examiner;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import java.beans.PropertyVetoException;
import java.util.Locale;

public class SpeechUtils {

    private SynthesizerModeDesc desc;
    private Synthesizer synthesizer;

    public void init() throws EngineException, AudioException, PropertyVetoException {
        if (desc == null) {

            // Set property as Kevin Dictionary
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

            desc = new SynthesizerModeDesc(Locale.US);

            // Register Engine
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            synthesizer = Central.createSynthesizer(desc);

            // Allocate synthesizer
            synthesizer.allocate();

            // Resume Synthesizer
            synthesizer.resume();

            // Create voice
            var voice = new Voice(
                    "kevin16",
                    javax.speech.synthesis.Voice.GENDER_DONT_CARE,
                    javax.speech.synthesis.Voice.AGE_DONT_CARE,
                    null
            );

            // Set voice
            synthesizer.getSynthesizerProperties().setVoice(voice);
        }

    }

    public void terminate() throws EngineException {
        synthesizer.deallocate();
    }

    public void speak(String speakText) throws InterruptedException {
        synthesizer.speakPlainText(speakText, null);
        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
    }

}
