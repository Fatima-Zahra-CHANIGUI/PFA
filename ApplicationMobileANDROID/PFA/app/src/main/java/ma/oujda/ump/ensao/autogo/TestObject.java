package ma.oujda.ump.ensao.autogo;

import java.io.Serializable;

public class TestObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private int PARAM_VITES_PAS;
    private int PARAM_I_FOR_GO_LEFT;
    private int PARAM_DELAY_GO_LEFT;
    private int PARAM_STOP_GO_LEFT;
    private int PARAM_I_FOR_GO_RIGHT;
    private int PARAM_DELAY_GO_RIGHT;
    private int PARAM_STOP_GO_RIGHT;
    private int PARAM_ANNUL_TEST;
    private int PARAM_I_FOR_GO_FORWARD;
    private int PARAM_CORRECTION;
    private int PARAM_VITES_L;
    private int PARAM_VITES_R;

    public TestObject(int PARAM_VITES_PAS , int PARAM_I_FOR_GO_LEFT , int PARAM_DELAY_GO_LEFT , int PARAM_STOP_GO_LEFT, int PARAM_I_FOR_GO_RIGHT, int PARAM_DELAY_GO_RIGHT, int PARAM_STOP_GO_RIGHT, int PARAM_ANNUL_TEST, int PARAM_I_FOR_GO_FORWARD, int PARAM_CORRECTION, int PARAM_VITES_L, int PARAM_VITES_R)
    {
        this.PARAM_VITES_PAS=PARAM_VITES_PAS;
        this.PARAM_I_FOR_GO_LEFT=PARAM_I_FOR_GO_LEFT;
        this.PARAM_DELAY_GO_LEFT=PARAM_DELAY_GO_LEFT;
        this.PARAM_STOP_GO_LEFT=PARAM_STOP_GO_LEFT;
        this.PARAM_I_FOR_GO_RIGHT=PARAM_I_FOR_GO_RIGHT;
        this.PARAM_DELAY_GO_RIGHT=PARAM_DELAY_GO_RIGHT;
        this.PARAM_STOP_GO_RIGHT=PARAM_STOP_GO_RIGHT;
        this.PARAM_ANNUL_TEST=PARAM_ANNUL_TEST;
        this.PARAM_I_FOR_GO_FORWARD=PARAM_I_FOR_GO_FORWARD;
        this.PARAM_CORRECTION=PARAM_CORRECTION;
        this.PARAM_VITES_L=PARAM_VITES_L;
        this.PARAM_VITES_R=PARAM_VITES_R;
    }

    public int getPARAM_VITES_PAS() {
        return PARAM_VITES_PAS;
    }

    public void setPARAM_VITES_PAS(int PARAM_VITES_PAS) {
        this.PARAM_VITES_PAS = PARAM_VITES_PAS;
    }

    public int getPARAM_I_FOR_GO_LEFT() {
        return PARAM_I_FOR_GO_LEFT;
    }

    public void setPARAM_I_FOR_GO_LEFT(int PARAM_I_FOR_GO_LEFT) {
        this.PARAM_I_FOR_GO_LEFT = PARAM_I_FOR_GO_LEFT;
    }

    public int getPARAM_DELAY_GO_LEFT() {
        return PARAM_DELAY_GO_LEFT;
    }

    public void setPARAM_DELAY_GO_LEFT(int PARAM_DELAY_GO_LEFT) {
        this.PARAM_DELAY_GO_LEFT = PARAM_DELAY_GO_LEFT;
    }

    public int getPARAM_STOP_GO_LEFT() {
        return PARAM_STOP_GO_LEFT;
    }

    public void setPARAM_STOP_GO_LEFT(int PARAM_STOP_GO_LEFT) {
        this.PARAM_STOP_GO_LEFT = PARAM_STOP_GO_LEFT;
    }

    public int getPARAM_I_FOR_GO_RIGHT() {
        return PARAM_I_FOR_GO_RIGHT;
    }

    public void setPARAM_I_FOR_GO_RIGHT(int PARAM_I_FOR_GO_RIGHT) {
        this.PARAM_I_FOR_GO_RIGHT = PARAM_I_FOR_GO_RIGHT;
    }

    public int getPARAM_DELAY_GO_RIGHT() {
        return PARAM_DELAY_GO_RIGHT;
    }

    public void setPARAM_DELAY_GO_RIGHT(int PARAM_DELAY_GO_RIGHT) {
        this.PARAM_DELAY_GO_RIGHT = PARAM_DELAY_GO_RIGHT;
    }

    public int getPARAM_STOP_GO_RIGHT() {
        return PARAM_STOP_GO_RIGHT;
    }

    public void setPARAM_STOP_GO_RIGHT(int PARAM_STOP_GO_RIGHT) {
        this.PARAM_STOP_GO_RIGHT = PARAM_STOP_GO_RIGHT;
    }

    public int getPARAM_ANNUL_TEST() {
        return PARAM_ANNUL_TEST;
    }

    public void setPARAM_ANNUL_TEST(int PARAM_ANNUL_TEST) {
        this.PARAM_ANNUL_TEST = PARAM_ANNUL_TEST;
    }

    public int getPARAM_I_FOR_GO_FORWARD() {
        return PARAM_I_FOR_GO_FORWARD;
    }

    public void setPARAM_I_FOR_GO_FORWARD(int PARAM_I_FOR_GO_FORWARD) {
        this.PARAM_I_FOR_GO_FORWARD = PARAM_I_FOR_GO_FORWARD;
    }

    public int getPARAM_CORRECTION() {
        return PARAM_CORRECTION;
    }

    public void setPARAM_CORRECTION(int PARAM_CORRECTION) {
        this.PARAM_CORRECTION = PARAM_CORRECTION;
    }

    public int getPARAM_VITES_L() {
        return PARAM_VITES_L;
    }

    public void setPARAM_VITES_L(int PARAM_VITES_L) {
        this.PARAM_VITES_L = PARAM_VITES_L;
    }

    public int getPARAM_VITES_R() {
        return PARAM_VITES_R;
    }

    public void setPARAM_VITES_R(int PARAM_VITES_R) {
        this.PARAM_VITES_R = PARAM_VITES_R;
    }
}
