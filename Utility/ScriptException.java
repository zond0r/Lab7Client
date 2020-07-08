//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utility;

public class ScriptException extends Exception {
    private String wrong = "";

    public ScriptException(String wrong) {
        this.wrong = wrong;
    }

    public String getWrong() {
        return this.wrong;
    }
}
