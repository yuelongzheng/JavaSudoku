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

# Future Improvements

- Add a log file for converted Websudoku sudoku
- Improve the GUI of the program
- Make the program into an application

