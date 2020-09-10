package project;
import static java.util.Map.entry; 
import java.util.Map;
import java.util.TreeMap;
public class Instruction {
	byte opcode;
	int arg;
	public static boolean noArgument(Instruction instr) {
		if(instr.opcode<24) return true;
		return false;
	}
	static int numOnes(int k) {
		int count=0;
		for(char c:Integer.toUnsignedString(k,2).toCharArray()) {
			if(c=='1') {
				count++;
			}
		}
		return count;
	}
	public Instruction(byte o, int a) {
		opcode=o;
		arg=a;
	}
	static void checkParity(Instruction instr) {
		if (numOnes(instr.opcode)%2==1) {
			throw new ParityCheckException("This instruction is corrupted.");
		}
		
	}
	public static Map<String, Integer> OPCODES = Map.ofEntries( entry("NOP", 0), entry("NOT", 1), entry("HALT",2), entry("LOD",3), entry("STO",4), entry("ADD",5), entry("SUB",6), entry("MUL",7), entry("DIV",8), entry("AND",9), entry("JUMP",10), entry("JMPZ",11), entry("CMPL",12), entry("CMPZ",13));
	public static final Map<Integer, String> MNEMONICS = new TreeMap<>();
	static { 
		for(String str : OPCODES.keySet())
		MNEMONICS.put(OPCODES.get(str), str);
		}
	public String getText() {
		int flag=opcode&6;
		String ap = "";
		if(flag==2) ap="#";
		else if(flag==4) ap="@";
		else if(flag==6) ap="&";
		StringBuilder buff=new StringBuilder(MNEMONICS.get(opcode/8));
		buff.append("  ");
		buff.append(ap);
		buff.append(Integer.toString(arg, 16));
		return buff.toString().toUpperCase();
	}
	public String getBinHex() {
		String str="00000000"+Integer.toString(opcode,2);
		StringBuilder buff=new StringBuilder(str.substring(str.length()-8));
		buff.append("  ");
		buff.append(Integer.toHexString(arg));
		return buff.toString().toUpperCase();
	}
	@Override
	public String toString() {
		return "Instruction ["+Integer.toString(opcode,2)+", "+Integer.toString(arg, 16)+"]";
	}
}
