package project;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public interface Assembler {
	Set<String> noArgument=new TreeSet(Arrays.asList("HALT", "NOP", "NOT"));
	int assemble(String inputFileName, String outputFileName, StringBuilder error);
	class DataPair{
		protected int fields, address, value;
		public DataPair(int a, int v) {
			address=a;
			value=v;
		}
		@Override
		public String toString() {
			return "DataPair (" + address + ", " + value + ")";
		}
	}
}
