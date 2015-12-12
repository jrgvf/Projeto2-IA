// Copyright (C) 2014 Guibing Guo
//
// This file is part of LibRec.
//
// LibRec is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// LibRec is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with LibRec. If not, see <http://www.gnu.org/licenses/>.
//

package librec.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import librec.util.FileIO;
import librec.util.Logs;
import librec.util.Strings;
import librec.util.Systems;

/**
 * A demo created for the UMAP'15 demo session, could be useful for other users.
 * 
 * @author Guo Guibing
 *
 */
public class Project {

	public static void main(String[] args) {
		try {
			new Project().execute(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void execute(String[] args) throws Exception {

		// config logger
		String dirPath = FileIO.makeDirPath("project");
		Logs.config(dirPath + "log4j.xml", true);

		// set the folder path for configuration files
		String configDirPath = FileIO.makeDirPath(dirPath, "config");

		// prepare candidate options
		List<String> candOptions = new ArrayList<>();
		candOptions.add("1: Run UserKNN, ItemKNN e Global Average;");

		int option = 0;
		boolean flag = false;
		Scanner reader = new Scanner(System.in);
		String configFile1 = "librec.conf";
		String configFile2 = "librec.conf";
		String configFile3 = "librec.conf";
		do {
			Logs.debug(Strings.toSection(candOptions));
			System.out.print("Please digit 1 to Run.");
			option = reader.nextInt();

			// print an empty line
			Logs.debug();
			flag = false;

			// get algorithm-specific configuration file
			switch (option) {
			case 1:
				configFile1 = "UserKNN.conf";
				configFile2 = "ItemKNN.conf";
				configFile3 = "GlobalAvg.conf";
				break;
			default:
				Logs.error("Wrong input!\n");
				Systems.pause();
				continue;
			}

			if (flag)
				break;

			// run algorithm
			LibRec librec1 = new LibRec();
			librec1.setConfigFiles(configDirPath + configFile1);
			librec1.execute(args);
			
			// run algorithm
			LibRec librec2 = new LibRec();
			librec2.setConfigFiles(configDirPath + configFile2);
			librec2.execute(args);
			
			// run algorithm
			LibRec librec3 = new LibRec();
			librec3.setConfigFiles(configDirPath + configFile3);
			librec3.execute(args);

			// await next command
			Logs.debug();
			Systems.pause();

		} while (option != -1);
		reader.close();

		Logs.debug("Thanks for trying out LibRec! See you again!");
	}
}
