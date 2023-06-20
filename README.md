# Votann

Votann is an informational program for creating your unit lists for Warhammer 40k.

## Installation

Download votann-0.1.0.jar

## Compile from source

1. Install [clojure](https://clojure.org/guides/install_clojure), openJDK, [leinagain](https://leiningen.org).

2. `run lein uberjar` (might need to `run lein deps` before if it doesnt do it automatically)

3. `java -jar  target/uberjar/votann-0.1.0-standalone.jar`

(Please note I did not include the images for these, so if you build from source you will not have the unit and enhancements tabs viewable reach out to me and I can provide those files until I create non-copyright versions)

## Usage

Navigate with clicking or tab or arrow keys and enter to select.

### Macos/Linux

Run the following command in your terminal or create a `.sh` script.

`$ java -jar votann-0.1.0.jar`

### Windows

You can create a `.bat` file with the above command. Set the absolute path of the jar file or place the `.bat` file in the same directory as the `.jar` file. Or run the above command in your terminal.

## License

Votann is an informational program for creating your unit lists for Warhammer 40k
Copyright (C) 2023  OxNull

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
