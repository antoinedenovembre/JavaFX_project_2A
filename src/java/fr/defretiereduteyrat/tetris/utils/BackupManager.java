package fr.defretiereduteyrat.tetris.utils;

import java.io.*;

/**
 * The type Backup manager.
 */
public class BackupManager {

    /**
     * The constant save path.
     */
    public static final String SAVE_PATH = "src/bin/save.bin";

    /**
     * Read save file int.
     *
     * @return the int
     * @throws Exception the exception
     */
    public static int readSaveFile() throws Exception {
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(SAVE_PATH))) {
            return (int) reader.readObject();
        } catch ( FileNotFoundException e ) {
            System.err.println(e.getMessage()) ;
        }
        return 0;
    }

    /**
     * Save to file.
     *
     * @param score  the score
     * @throws Exception the exception
     */
    public static void saveToFile(int score) throws Exception {
        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(SAVE_PATH))) {
            writer.writeObject(score);
        } catch ( FileNotFoundException e ) {
            System.err.println(e.getMessage()) ;
        }
    }
}
