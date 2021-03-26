#  File: MagicSquares.py
#  Description: This program will create a magic square for a square with
#               an odd number of sides.
#  Timothy A. Meek II
#
#  Date Created: 04/16/2020
#  Date Last Modified: 04/22/2020

class MagicSquare():

    def __init__(self,side):

        self.side = side

        matrix = []

        # make side by side grid filled with 0's
        for row in range(side):
            r = []
            for col in range(side):
                r.append(0)
            matrix.append(r)

        # replace 0's with numbers 1 through side**2
        for i in range(1,side**2 +1):
        
            if i == 1:
                matrix[0][side//2] = i
                row = 0
                col = side//2
            
            elif (i-1) % side == 0:
                row += 1
                if row > side -1:
                    row = 0
                matrix[row][col] = i
            else:
                row -= 1
                col += 1
                if row < 0 and col > side -1:
                    row = side -1
                    col = 0
                elif row < 0:
                    row = side -1
                elif col > side -1:
                    col = 0
                    
                matrix[row][col] = i

        self.matrix = matrix

    def display(self):

        # formats the matrix formed in the __init__ method

        numRows = len(self.matrix)
        numCols = len(self.matrix[0])

        for row in range(numRows):
            for col in range(numCols):
                print(format(self.matrix[row][col],"5d"),end="")
            print("")

def main():

    # loop that creates a Magic Square for 1, 3, 5, 7, 9, 11, and 13
    for x in range(1,14,2):
        print("Magic Square of size", x)
        print()
        x = MagicSquare(x)
        x.display()
        print()

main()
