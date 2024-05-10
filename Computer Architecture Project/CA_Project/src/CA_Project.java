import javax.swing.plaf.synth.SynthLookAndFeel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Stack;

public class CA_Project {

    static int instructionMemory[] = new int[1024];
    static int dataMemory[] = new int[2048];
    static int GPRS [] = new int[64];
    static int instruction = 0;
    static int pc = 0;
    static int statusRegister = 0;
    static int numOfInstructions = 0;
    static int clockCycles = 0;
    static int opcode = 0;
    static int operand1 = 0;
    static int operand2 = 0;
    static int destinationRegister = 0;
    static int lastClockBeforeJump = 0;

    public static void parser() {

        try (BufferedReader br = new BufferedReader(new FileReader("Program.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                numOfInstructions++;
                String lineToBits = "";
                String[] splitted = line.split(" ");

                if (splitted[0].equals("ADD")) {
                    DecimalFormat df = new DecimalFormat("000000");
                    String a = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[1].substring(1)))));
                    String b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2].substring(1)))));
                    lineToBits = lineToBits + "0000" + a + b;

                    instructionMemory[numOfInstructions - 1] = Integer.parseInt(lineToBits, 2);
                }

                else if (splitted[0].equals("SUB")) {
                    DecimalFormat df = new DecimalFormat("000000");
                    String a = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[1].substring(1)))));
                    String b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2].substring(1)))));
                    lineToBits = lineToBits + "0001" + a + b;

                    instructionMemory[numOfInstructions-1] = Integer.parseInt(lineToBits, 2);
                }

                else if (splitted[0].equals("MUL")) {
                    DecimalFormat df = new DecimalFormat("000000");
                    String a = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[1].substring(1)))));
                    String b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2].substring(1)))));
                    lineToBits = lineToBits + "0010" + a + b;

                    instructionMemory[numOfInstructions-1] = Integer.parseInt(lineToBits, 2);
                }

                else if (splitted[0].equals("LDI")) {
                    DecimalFormat df = new DecimalFormat("000000");
                    String b = "";
                    String a = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[1].substring(1)))));
                    if (Integer.toBinaryString(Integer.parseInt(splitted[2])).length() > 6){
                        b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2])).substring(26)));
                    }
                    else {
                        b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2]))));
                    }

                    lineToBits = lineToBits + "0011" + a + b;

                    instructionMemory[numOfInstructions-1] = Integer.parseInt(lineToBits, 2);
                }

                else if (splitted[0].equals("BEQZ")) {
                    DecimalFormat df = new DecimalFormat("000000");
                    String b = "";
                    String a = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[1].substring(1)))));
                    if (Integer.toBinaryString(Integer.parseInt(splitted[2])).length() > 6){
                        b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2])).substring(26)));
                    }
                    else {
                        b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2]))));
                    }

                    lineToBits = lineToBits + "0100" + a + b;

                    instructionMemory[numOfInstructions-1] = Integer.parseInt(lineToBits, 2);
                }

                else if (splitted[0].equals("AND")) {
                    DecimalFormat df = new DecimalFormat("000000");
                    String a = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[1].substring(1)))));
                    String b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2].substring(1)))));
                    lineToBits = lineToBits + "0101" + a + b;

                    instructionMemory[numOfInstructions-1] = Integer.parseInt(lineToBits, 2);
                }

                else if (splitted[0].equals("OR")) {
                    DecimalFormat df = new DecimalFormat("000000");
                    String a = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[1].substring(1)))));
                    String b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2].substring(1)))));
                    lineToBits = lineToBits + "0110" + a + b;

                    instructionMemory[numOfInstructions-1] = Integer.parseInt(lineToBits, 2);
                }

                else if (splitted[0].equals("JR")) {
                    DecimalFormat df = new DecimalFormat("000000");
                    String a = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[1].substring(1)))));
                    String b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2].substring(1)))));
                    lineToBits = lineToBits + "0111" + a + b;

                    instructionMemory[numOfInstructions-1] = Integer.parseInt(lineToBits, 2);
                }

                else if (splitted[0].equals("SLC")) {
                    DecimalFormat df = new DecimalFormat("000000");
                    String b = "";
                    String a = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[1].substring(1)))));
                    if (Integer.toBinaryString(Integer.parseInt(splitted[2])).length() > 6){
                        b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2])).substring(26)));
                    }
                    else {
                        b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2]))));
                    }
                    lineToBits = lineToBits + "1000" + a + b;

                    instructionMemory[numOfInstructions-1] = Integer.parseInt(lineToBits, 2);
                }

                else if (splitted[0].equals("SRC")) {
                    DecimalFormat df = new DecimalFormat("000000");
                    String b = "";
                    String a = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[1].substring(1)))));
                    if (Integer.toBinaryString(Integer.parseInt(splitted[2])).length() > 6){
                        b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2])).substring(26)));
                    }
                    else {
                        b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2]))));
                    }
                    lineToBits = lineToBits + "1001" + a + b;

                    instructionMemory[numOfInstructions-1] = Integer.parseInt(lineToBits, 2);
                }

                else if (splitted[0].equals("LB")) {
                    DecimalFormat df = new DecimalFormat("000000");
                    String a = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[1].substring(1)))));
                    String b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2]))));
                    lineToBits = lineToBits + "1010" + a + b;

                    instructionMemory[numOfInstructions-1] = Integer.parseInt(lineToBits, 2);
                }

                else if (splitted[0].equals("SB")) {
                    DecimalFormat df = new DecimalFormat("000000");
                    String a = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[1].substring(1)))));
                    String b = df.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(splitted[2]))));
                    lineToBits = lineToBits + "1011" + a + b;

                    instructionMemory[numOfInstructions-1] = Integer.parseInt(lineToBits, 2);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void loop(Integer pc2, Integer clockCycles2) {

        Boolean broken1 = false;
        Boolean broken2 = false;

        pc = pc2;
        clockCycles = clockCycles2;
        for (int i = 1; i <= clockCycles; i++) {
            System.out.println("Clock cycle: " + (i+lastClockBeforeJump));

            if (i == 1) {
                fetch(pc); //Fetching instruction 1
            }
            else if (i == 2) {
                if (i == clockCycles - 1){
                    decode(instruction); //Decoding instruction 1
                }
                else {
                    decode(instruction); //Decoding instruction 1
                    fetch(pc); //Fetching instruction 2
                }
            }
            else if (i == clockCycles - 1) {
                Integer pcFromLastCycle = pc; //Storing the old pc to not cause errors in the case we are executing a JR or a BEQZ instruction (to update the pc in the following clock cycle (not the current one))
                execute(opcode, destinationRegister, operand1, operand2); //Executing the instruction before the last one
                decode(instruction); //Decoding last instruction
                if (pc != pcFromLastCycle){
                    lastClockBeforeJump = lastClockBeforeJump + i;
                    broken1 = true;
                    System.out.println("=============================");
                    break;
                }
                //pc++; //pc needs to get increment here because there is no fetch incase of a BEQZ instruction
                //Turns out that pc doesn't need to get incremented here, I will still leave this pc++ in case I need it
            }
            else if (i == clockCycles) {
                Integer pcFromLastCycle = pc; //Storing the old pc to not cause errors in the case we are executing a JR or a BEQZ instruction (to update the pc in the following clock cycle (not the current one))
                execute(opcode, destinationRegister, operand1, operand2); //Executing last instruction
                if (pc != pcFromLastCycle){
                    lastClockBeforeJump = lastClockBeforeJump + i;
                    broken1 = true;
                    System.out.println("=============================");
                    break;
                }
            }
            else {
                Integer pcFromLastCycle = pc; //Storing the old pc to not cause errors in the case we are executing a JR or a BEQZ instruction (to update the pc in the following clock cycle (not the current one))
                execute(opcode, destinationRegister, operand1, operand2); //Executing instruction 1
                decode(instruction); //Decoding instruction 2
                fetch(pcFromLastCycle); //Fetching instruction 3
                if ((pc-1) != pcFromLastCycle){
                    lastClockBeforeJump = lastClockBeforeJump + i;
                    broken2 = true;
                    System.out.println("=============================");
                    break;
                }
            }

            System.out.println("=============================");
        }

        if (broken1 == true){
            loop((pc), clockCycles);
        }

        if (broken2 == true){
            loop((pc-1), clockCycles);
        }

        if (broken1 == false && broken2 == false) {
            System.out.println("Registers: " + Arrays.toString(GPRS));
            System.out.println("Instruction memory: " + Arrays.toString(instructionMemory));
            System.out.println("Data memory: " + Arrays.toString(dataMemory));
        }
    }

    public static void fetch(Integer pc2) {

        instruction = instructionMemory[pc2];
        System.out.println("Fetch: Fetching instruction at pc = " + pc2 + " which is: " + instruction);
        pc++;

    }

    public static void decode(int instruction) {

        System.out.println("Decode: Decoding instruction: " + instruction);

        opcode = (instruction & 0b1111000000000000) >> 12;
        operand1 = GPRS[(instruction & 0b0000111111000000) >> 6];
        destinationRegister = (instruction & 0b0000111111000000) >> 6;


        //ADD - SUB - MUL - AND - OR - JR  (R-Type instructions)
        if ((opcode == 0b0000) || (opcode == 0b0001) || (opcode == 0b0010) || (opcode == 0b0101) || (opcode == 0b0110) || (opcode == 0b0111)){
            operand2 = GPRS[(instruction & 0b0000000000111111)];
        }

        //LDI - BEQZ - SLC - SRC - LB - SB (I-Type instructions)
        else {
            operand2 = (instruction & 0b0000000000111111);
        }

    }

    public static void execute (Integer opcode, Integer destinationRegister, Integer operand1, Integer operand2){

        statusRegister = 0;

        if(opcode == 0){

            //<editor-fold desc="Overflow flag">
            if (operand1 >= 0 && operand2 >= 0){
                if (((byte)(operand1 + operand2)) < 0){
                    statusRegister = (byte) (statusRegister | (1 << 3)); //Set the overflow bit to 1
                }
                else {
                    statusRegister = (byte) (statusRegister & ~(1 << 3)); //Set the overflow bit to 0
                }
            }
            if (operand1 < 0 && operand2 < 0){
                if (((byte)(operand1 + operand2)) >= 0){
                    statusRegister = (byte) (statusRegister | (1 << 3)); //Set the overflow bit to 1
                }
                else {
                    statusRegister = (byte) (statusRegister & ~(1 << 3)); //Set the overflow bit to 0
                }
            }
            if (operand1 < 0 && operand2 >= 0){
                statusRegister = (byte) (statusRegister & ~(1 << 3)); //Set the overflow bit to 0
            }
            if (operand1 >= 0 && operand2 < 0){
                statusRegister = (byte) (statusRegister & ~(1 << 3)); //Set the overflow bit to 0
            }
            //</editor-fold>

            //<editor-fold desc="Carry flag">
            if (((((operand1 & 0xff) + (operand2 & 0xff)) & 0b100000000) >> 8) == 1){
                statusRegister = (byte) (statusRegister | (1 << 4)); //Set the carry bit to 1
            }
            if (((((operand1 & 0xff) + (operand2 & 0xff)) & 0b100000000) >> 8) == 0){
                statusRegister = (byte) (statusRegister & ~(1 << 4)); //Set the carry bit to 0
            }
            //</editor-fold>

            System.out.println("Oppcode: " + opcode + " || Operand 1: " + operand1 + " || Operand 2: " + operand2);
            GPRS[destinationRegister] = (byte) (((byte) Integer.parseInt(operand1.toString())) + ((byte) Integer.parseInt(operand2.toString())));
            System.out.println("Execute: Register[" + destinationRegister + "] value after the ADD instruction: " + GPRS[destinationRegister]);

            //<editor-fold desc="Negative flag">
            if (GPRS[destinationRegister] < 0){
                statusRegister = (byte) (statusRegister | (1 << 2)); //Set the negative bit to 1
            }
            if (GPRS[destinationRegister] >= 0){
                statusRegister = (byte) (statusRegister & ~(1 << 2)); //Set the negative bit to 0
            }
            //</editor-fold>

            //<editor-fold desc="Sign flag">
            if( (((statusRegister & 0b000100) >> 2) ^ ((statusRegister & 0b001000) >> 3)) == 0){
                statusRegister = (byte) (statusRegister & ~(1 << 1)); //Set the sign bit to 0
            }
            if( (((statusRegister & 0b000100) >> 2) ^ ((statusRegister & 0b001000) >> 3)) == 1){
                statusRegister = (byte) (statusRegister | (1 << 1)); //Set the sign bit to 1
            }
            //</editor-fold>

            //<editor-fold desc="Zero flag">
            if (GPRS[destinationRegister] == 0){
                statusRegister = (byte) (statusRegister | (1 << 0)); //Set the zero bit to 1
            }
            if (GPRS[destinationRegister] != 0){
                statusRegister = (byte) (statusRegister & ~(1 << 0)); //Set the zero bit to 0
            }
            //</editor-fold>

            DecimalFormat df = new DecimalFormat("00000000");
            System.out.println("Status register after executing the instruction = " + df.format(Integer.parseInt(Integer.toBinaryString(statusRegister))));
        }

        else if(opcode == 1){

            //<editor-fold desc="Overflow flag">
            if (operand1 >= 0 && operand2 < 0){
                if (((byte)(operand1 - operand2)) < 0){
                    statusRegister = (byte) (statusRegister | (1 << 3)); //Set the overflow bit to 1
                }
                else {
                    statusRegister = (byte) (statusRegister & ~(1 << 3)); //Set the overflow bit to 0
                }
            }
            if (operand1 < 0 && operand2 >= 0){
                if (((byte)(operand1 - operand2)) >= 0){
                    statusRegister = (byte) (statusRegister | (1 << 3)); //Set the overflow bit to 1
                }
                else {
                    statusRegister = (byte) (statusRegister & ~(1 << 3)); //Set the overflow bit to 0
                }
            }
            if (operand1 >= 0 && operand2 >= 0){
                statusRegister = (byte) (statusRegister & ~(1 << 3)); //Set the overflow bit to 0
            }
            if (operand1 < 0 && operand2 < 0){
                statusRegister = (byte) (statusRegister & ~(1 << 3)); //Set the overflow bit to 0
            }
            //</editor-fold>

            //<editor-fold desc="Carry flag">
            if ((((operand1 - operand2) & 0b100000000) >> 8) == 1){
                statusRegister = (byte) (statusRegister | (1 << 4)); //Set the carry bit to 1
            }
            if ((((operand1 - operand2) & 0b100000000) >> 8) == 0){
                statusRegister = (byte) (statusRegister & ~(1 << 4)); //Set the carry bit to 0
            }
            //</editor-fold>

            System.out.println("Oppcode: " + opcode + " || Operand 1: " + operand1 + " || Operand 2: " + operand2);
            GPRS[destinationRegister] = (byte) (((byte) Integer.parseInt(operand1.toString())) - ((byte) Integer.parseInt(operand2.toString())));
            System.out.println("Execute: Register[" + destinationRegister + "] value after the SUB instruction: " + GPRS[destinationRegister]);

            //<editor-fold desc="Negative flag">
            if (GPRS[destinationRegister] < 0){
                statusRegister = (byte) (statusRegister | (1 << 2)); //Set the negative bit to 1
            }
            if (GPRS[destinationRegister] >= 0){
                statusRegister = (byte) (statusRegister & ~(1 << 2)); //Set the negative bit to 0
            }
            //</editor-fold>

            //<editor-fold desc="Sign flag">
            if( (((statusRegister & 0b000100) >> 2) ^ ((statusRegister & 0b001000) >> 3)) == 0){
                statusRegister = (byte) (statusRegister & ~(1 << 1)); //Set the sign bit to 0
            }
            if( (((statusRegister & 0b000100) >> 2) ^ ((statusRegister & 0b001000) >> 3)) == 1){
                statusRegister = (byte) (statusRegister | (1 << 1)); //Set the sign bit to 1
            }
            //</editor-fold>

            //<editor-fold desc="Zero flag">
            if (GPRS[destinationRegister] == 0){
                statusRegister = (byte) (statusRegister | (1 << 0)); //Set the zero bit to 1
            }
            if (GPRS[destinationRegister] != 0){
                statusRegister = (byte) (statusRegister & ~(1 << 0)); //Set the zero bit to 0
            }
            //</editor-fold>

            DecimalFormat df = new DecimalFormat("00000000");
            System.out.println("Status register after executing the instruction = " + df.format(Integer.parseInt(Integer.toBinaryString(statusRegister))));
        }

        else if(opcode == 2){

            //<editor-fold desc="Carry flag">
            if ((((operand1 * operand2) & 0b100000000) >> 8) == 1){
                statusRegister = (byte) (statusRegister | (1 << 4)); //Set the carry bit to 1
            }
            if ((((operand1 * operand2) & 0b100000000) >> 8) == 0){
                statusRegister = (byte) (statusRegister & ~(1 << 4)); //Set the carry bit to 0
            }
            //</editor-fold>

            System.out.println("Oppcode: " + opcode + " || Operand 1: " + operand1 + " || Operand 2: " + operand2);
            GPRS[destinationRegister] = (byte) (((byte) Integer.parseInt(operand1.toString())) * ((byte) Integer.parseInt(operand2.toString())));
            System.out.println("Execute: Register[" + destinationRegister + "] value after the MUL instruction: " + GPRS[destinationRegister]);

            //<editor-fold desc="Negative flag">
            if (GPRS[destinationRegister] < 0){
                statusRegister = (byte) (statusRegister | (1 << 2)); //Set the negative bit to 1
            }
            if (GPRS[destinationRegister] >= 0){
                statusRegister = (byte) (statusRegister & ~(1 << 2)); //Set the negative bit to 0
            }
            //</editor-fold>

            //<editor-fold desc="Zero flag">
            if (GPRS[destinationRegister] == 0){
                statusRegister = (byte) (statusRegister | (1 << 0)); //Set the zero bit to 1
            }
            if (GPRS[destinationRegister] != 0){
                statusRegister = (byte) (statusRegister & ~(1 << 0)); //Set the zero bit to 0
            }
            //</editor-fold>

            DecimalFormat df = new DecimalFormat("00000000");
            System.out.println("Status register after executing the instruction = " + df.format(Integer.parseInt(Integer.toBinaryString(statusRegister))));
        }

        else if(opcode == 3){

            //Extending the operand2 value based on its sign
            if ((operand2 & 0b100000) == 0){
                operand2 = Integer.valueOf((byte) (operand2 & ~(1 << 6)));
                operand2 = Integer.valueOf((byte) (operand2 & ~(1 << 7)));
            }
            else {
                operand2 = Integer.valueOf((byte) (operand2 | (1 << 6)));
                operand2 = Integer.valueOf((byte) (operand2 | (1 << 7)));
            }

            System.out.println("Oppcode: " + opcode + " || Operand 1: " + operand1 + " || Operand 2: " + operand2);
            GPRS[destinationRegister] = operand2;
            System.out.println("Execute: Register[" + destinationRegister + "] value after the LDI instruction: " + GPRS[destinationRegister]);
        }

        else if(opcode == 4){

            //Extending the operand2 value based on its sign
            if ((operand2 & 0b100000) == 0){
                operand2 = Integer.valueOf((byte) (operand2 & ~(1 << 6)));
                operand2 = Integer.valueOf((byte) (operand2 & ~(1 << 7)));
            }
            else {
                operand2 = Integer.valueOf((byte) (operand2 | (1 << 6)));
                operand2 = Integer.valueOf((byte) (operand2 | (1 << 7)));
            }

            System.out.println("Oppcode: " + opcode + " || Operand 1: " + operand1 + " || Operand 2: " + operand2);

            if (operand1 == 0){

                Integer oldPC = pc;
                pc = pc + 1 + (operand2);
                System.out.println("Execute: PC register value after the BEQZ instruction: " + pc);
                long skipped = (((long) (pc+1)) - oldPC); //The number of instructions that will be skipped
                clockCycles = (int) (3 + (((numOfInstructions - (oldPC-1) - skipped) - 1) * 1)); //Number of total clock cycles after removing the skipped instructions
                //pc--; //Because the fetch will add 1 to the pc and in the case of JR or BEQZ we only need the updated pc
            }
            if (operand1 != 0){
                System.out.println("Execute: PC register value after the BEQZ instruction (nothing changed): " + pc);
            }

        }

        else if(opcode == 5){

            System.out.println("Oppcode: " + opcode + " || Operand 1: " + operand1 + " || Operand 2: " + operand2);
            GPRS[destinationRegister] = Integer.parseInt(operand1.toString()) & Integer.parseInt(operand2.toString());
            System.out.println("Execute: Register[" + destinationRegister + "] value after the AND instruction: " + GPRS[destinationRegister]);

            //<editor-fold desc="Negative flag">
            if (GPRS[destinationRegister] < 0){
                statusRegister = (byte) (statusRegister | (1 << 2)); //Set the negative bit to 1
            }
            if (GPRS[destinationRegister] >= 0){
                statusRegister = (byte) (statusRegister & ~(1 << 2)); //Set the negative bit to 0
            }
            //</editor-fold>

            //<editor-fold desc="Zero flag">
            if (GPRS[destinationRegister] == 0){
                statusRegister = (byte) (statusRegister | (1 << 0)); //Set the zero bit to 1
            }
            if (GPRS[destinationRegister] != 0){
                statusRegister = (byte) (statusRegister & ~(1 << 0)); //Set the zero bit to 0
            }
            //</editor-fold>

            DecimalFormat df = new DecimalFormat("00000000");
            System.out.println("Status register after executing the instruction = " + df.format(Integer.parseInt(Integer.toBinaryString(statusRegister))));
        }

        else if(opcode == 6){

            System.out.println("Oppcode: " + opcode + " || Operand 1: " + operand1 + " || Operand 2: " + operand2);
            GPRS[destinationRegister] = Integer.parseInt(operand1.toString()) | Integer.parseInt(operand2.toString());
            System.out.println("Execute: Register[" + destinationRegister + "] value after the OR instruction: " + GPRS[destinationRegister]);

            //<editor-fold desc="Negative flag">
            if (GPRS[destinationRegister] < 0){
                statusRegister = (byte) (statusRegister | (1 << 2)); //Set the negative bit to 1
            }
            if (GPRS[destinationRegister] >= 0){
                statusRegister = (byte) (statusRegister & ~(1 << 2)); //Set the negative bit to 0
            }
            //</editor-fold>

            //<editor-fold desc="Zero flag">
            if (GPRS[destinationRegister] == 0){
                statusRegister = (byte) (statusRegister | (1 << 0)); //Set the zero bit to 1
            }
            if (GPRS[destinationRegister] != 0){
                statusRegister = (byte) (statusRegister & ~(1 << 0)); //Set the zero bit to 0
            }
            //</editor-fold>

            DecimalFormat df = new DecimalFormat("00000000");
            System.out.println("Status register after executing the instruction = " + df.format(Integer.parseInt(Integer.toBinaryString(statusRegister))));
        }

        else if(opcode == 7){
            DecimalFormat df = new DecimalFormat("00000000");
            String a = df.format(Integer.parseInt(Long.toString(operand1, 2)));
            String b = df.format(Integer.parseInt(Long.toString(operand2, 2)));
            if (Long.toString(operand1, 2).charAt(0) == '-'){
                a = df.format(Integer.parseInt(Long.toString(operand1, 2).substring(1)));
            }
            if (Long.toString(operand2, 2).charAt(0) == '-'){
                b = df.format(Integer.parseInt(Long.toString(operand2, 2).substring(1)));
            }

            Integer oldPC = pc;
            pc = Integer.parseInt((a + "" + b), 2);
            System.out.println("Oppcode: " + opcode + " || Operand 1: " + operand1 + " || Operand 2: " + operand2);
            System.out.println("Execute: PC register value after the JR instruction: " + pc);
            long skipped = (((long) (pc+1)) - oldPC); //The number of instructions that will be skipped
            clockCycles = (int) (3 + (((numOfInstructions - (oldPC-1) - skipped) - 1) * 1)); //Number of total clock cycles after removing the skipped instructions
            //pc--; //Because the fetch will add 1 to the pc and in the case of JR or BEQZ we only need the updated pc

        }

        else if(opcode == 8){

            //Extending the operand2 value based on its sign
            if ((operand2 & 0b100000) == 0){
                operand2 = Integer.valueOf((byte) (operand2 & ~(1 << 6)));
                operand2 = Integer.valueOf((byte) (operand2 & ~(1 << 7)));
            }
            else {
                operand2 = Integer.valueOf((byte) (operand2 | (1 << 6)));
                operand2 = Integer.valueOf((byte) (operand2 | (1 << 7)));
            }

            GPRS[destinationRegister] = (byte) ((operand1 << operand2) | (operand1 >>> (8-operand2)));
            System.out.println("Oppcode: " + opcode + " || Operand 1: " + operand1 + " || Operand 2: " + operand2);
            System.out.println("Execute: Register[" + destinationRegister + "] value after the SLC instruction: " + GPRS[destinationRegister]);

            //<editor-fold desc="Negative flag">
            if (GPRS[destinationRegister] < 0){
                statusRegister = (byte) (statusRegister | (1 << 2)); //Set the negative bit to 1
            }
            if (GPRS[destinationRegister] >= 0){
                statusRegister = (byte) (statusRegister & ~(1 << 2)); //Set the negative bit to 0
            }
            //</editor-fold>

            //<editor-fold desc="Zero flag">
            if (GPRS[destinationRegister] == 0){
                statusRegister = (byte) (statusRegister | (1 << 0)); //Set the zero bit to 1
            }
            if (GPRS[destinationRegister] != 0){
                statusRegister = (byte) (statusRegister & ~(1 << 0)); //Set the zero bit to 0
            }
            //</editor-fold>

            DecimalFormat df = new DecimalFormat("00000000");
            System.out.println("Status register after executing the instruction = " + df.format(Integer.parseInt(Integer.toBinaryString(statusRegister))));
        }

        else if(opcode == 9){

            //Extending the operand2 value based on its sign
            if ((operand2 & 0b100000) == 0){
                operand2 = Integer.valueOf((byte) (operand2 & ~(1 << 6)));
                operand2 = Integer.valueOf((byte) (operand2 & ~(1 << 7)));
            }
            else {
                operand2 = Integer.valueOf((byte) (operand2 | (1 << 6)));
                operand2 = Integer.valueOf((byte) (operand2 | (1 << 7)));
            }

            System.out.println("Oppcode: " + opcode + " || Operand 1: " + operand1 + " || Operand 2: " + operand2);
            GPRS[destinationRegister] = (byte) ((operand1 >>> operand2) | (operand1 << (8-operand2)));
            System.out.println("Execute: Register[" + destinationRegister + "] value after the SRC instruction: " + GPRS[destinationRegister]);

            //<editor-fold desc="Negative flag">
            if (GPRS[destinationRegister] < 0){
                statusRegister = (byte) (statusRegister | (1 << 2)); //Set the negative bit to 1
            }
            if (GPRS[destinationRegister] >= 0){
                statusRegister = (byte) (statusRegister & ~(1 << 2)); //Set the negative bit to 0
            }
            //</editor-fold>

            //<editor-fold desc="Zero flag">
            if (GPRS[destinationRegister] == 0){
                statusRegister = (byte) (statusRegister | (1 << 0)); //Set the zero bit to 1
            }
            if (GPRS[destinationRegister] != 0){
                statusRegister = (byte) (statusRegister & ~(1 << 0)); //Set the zero bit to 0
            }
            //</editor-fold>

            DecimalFormat df = new DecimalFormat("00000000");
            System.out.println("Status register after executing the instruction = " + df.format(Integer.parseInt(Integer.toBinaryString(statusRegister))));
        }

        else if(opcode == 10){
            GPRS[destinationRegister] = dataMemory[operand2];
            System.out.println("Oppcode: " + opcode + " || Operand 1: " + operand1 + " || Operand 2: " + operand2);
            System.out.println("Execute: Register[" + destinationRegister + "] value after the LB instruction: " + GPRS[destinationRegister]);
        }

        else if(opcode == 11){
            dataMemory[operand2] = operand1;
            System.out.println("Oppcode: " + opcode + " || Operand 1: " + operand1 + " || Operand 2: " + operand2);
            System.out.println("Execute: Memory[" + operand2 + "] value after the SB instruction: " + GPRS[destinationRegister]);
        }

    }

    public static void main(String[] args) {
        parser();
        clockCycles = 3 + ((numOfInstructions - 1) * 1);
        loop(pc, clockCycles);
    }

}