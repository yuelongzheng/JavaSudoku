# JavaSudoku
Takes an existing sudoku and automatically imports it into
[Sudokupad](https://app.crackingthecryptic.com/).
Uses [f-puzzles](https://www.f-puzzles.com/) to set
the sudoku and then uses this [conversion site](https://marktekfan.github.io/sudokupad-penpa-import/)
to import the sudoku into Sudokupad. 

The sudoku is converted into a JSON, and then the string of the 
JSON is compressed using [lz-string](https://github.com/tommyettinger/BlazingChain).

Available choices are 
- [New York Times](https://www.nytimes.com/puzzles/sudoku)
- [Websudoku](https://www.websudoku.com/)
- [sudoku.com.au](https://sudoku.com.au/)

All difficulties of all the websites can be chosen

# Runtime Environment
- Java runtime : Open JDK 17

# Creating a Mac .dmg

In the /JavaSudoku folder run the following 
    
    `jpackage --name Import-to-SudokuPad --input . --main-jar out/artifacts/javasudoku_jar/javasudoku.jar --icon src/main/resources/SudokuPad.icns`
This creates the Import-to-SudokuPad-1.0.dmg file.

![warning image](/src/main/resources/Warning.png)

A warning that the file is damaged and can't be opened can be dealt with by going to System Settings > Security and clicking open anyway

# Future Improvements
- Add a log file for converted Websudoku sudoku
- Improve the GUI of the program
- Make the program into a Windows Executable
- Be able to save sudokus
- Be able to change the shortcut buttons