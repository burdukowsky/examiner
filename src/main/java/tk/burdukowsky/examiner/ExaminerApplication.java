package tk.burdukowsky.examiner;

import java.beans.PropertyVetoException;
import javax.speech.AudioException;
import javax.speech.EngineException;

public class ExaminerApplication {

    public static void main(String[] args)
            throws AudioException, EngineException, PropertyVetoException, InterruptedException {
        SpeechUtils su = new SpeechUtils();
        su.init();
        su.speak("What university did you graduate from?");
        su.terminate();
    }

}
