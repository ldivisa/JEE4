import ""  
 public class FreeTts {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            voice.speak("Hello, this is a text-to-speech example using FreeTTS.");
            voice.deallocate();
        } else {
            System.out.println("Voice not found!");
        }
    }
    }

