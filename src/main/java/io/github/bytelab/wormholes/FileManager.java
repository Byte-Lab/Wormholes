/*
 * Copyright (c) 2014-2014
 * Byte-Lab <http://bytelab.pw>
 *
 * This file is part of Wormholes.
 *
 * Wormholes is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package main.java.io.github.bytelab.wormholes;

import java.io.*;
import java.util.logging.Logger;

public class FileManager extends Main{

    static Logger logger = Logger.getLogger("Minecraft");

    static void save(File config, File file)
    {
        try{
            if(!file.exists())
            {
                file.createNewFile();
            }

            ObjectOutputStream saveFile = new ObjectOutputStream(new FileOutputStream(file));
            saveFile.writeObject(config);
            saveFile.flush();
            saveFile.close();
        }
        catch(Exception exception)
        {
            logger.info("§cError occurred while saving files!");
        }
    }
    static Object load(File file)
    {
        try
        {
            ObjectInputStream loadFile = new ObjectInputStream(new FileInputStream(file));
            Object list = loadFile.readObject();
            loadFile.close();
            return list;
        }
        catch(Exception exception)
        {
            logger.info("§cError occurred while loading files!");
            return null;
        }
    }
}
