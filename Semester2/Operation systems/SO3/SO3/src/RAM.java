public class RAM {

    int memorySize;
    int[] memoryFrame;

    public RAM(int memorySize){
        this.memorySize = memorySize;
        this.memoryFrame = new int[memorySize];
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public int[] getMemoryFrame() {
        return memoryFrame;
    }

    public void setMemoryFrame(int[] memoryFrame) {
        this.memoryFrame = memoryFrame;
    }
    
}
