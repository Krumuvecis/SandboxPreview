package spaceProgramTest;

import main.SpaceProgram;

import static consoleUtils.SimplePrinting.printLine;

//
public class SpaceProgramTest {
    private static final SpaceProgram SPACE_PROGRAM = new SpaceProgram(
            new SpaceProgram.SpaceProgramState(new SpaceProgram.SpaceProgramAssets(
                    null,
                    null,
                    null)),
            new SpaceProgram.SpaceProgramState(new SpaceProgram.SpaceProgramAssets(
                    null,
                    null,
                    null)));

    //
    public static void main(String[] args) {
        printLine("Testing space program!");

        //TODO: finish this

        printSpaceProgramState(SPACE_PROGRAM.getInitialState());
        printSpaceProgramState(SPACE_PROGRAM.getEndState());

        printSpaceProgramAssets(SPACE_PROGRAM.getMissingAssets());
    }

    private static void printSpaceProgramState(SpaceProgram.SpaceProgramState state) {
        printSpaceProgramAssets(state.getAssets());
        //TODO: finish this
    }

    private static void printSpaceProgramAssets(SpaceProgram.SpaceProgramAssets assets) {
        //TODO: finish this
    }
}