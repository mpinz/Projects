package project;

public class Test {
	public static void main(String[] args) {
		Instruction instr=new Instruction((byte)0b00000011,1);
		Instruction.checkParity(instr);
		Instruction instr2=new Instruction((byte)0b00000111,1);
		Instruction.checkParity(instr2);
	}
}
