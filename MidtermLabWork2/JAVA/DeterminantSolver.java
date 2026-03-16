/**
 * =====================================================
 * Student Name    : DUPA, NEOMI ASHLEY SABIEN I.
 * Course          : Math 101 — Linear Algebra
 * Assignment      : Programming Assignment 1 — 3x3 Matrix Determinant Solver
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : March 16, 2025
 * GitHub Repo     : https://github.com/uphsd-cs-dupa-neomiashley
 *
 * Description:
 *   Solves for the determinant of my assigned 3x3 matrix step by step.
 *   I used cofactor expansion along the first row — meaning I break the
 *   3x3 into three smaller 2x2 minors, compute each one, apply the
 *   alternating +/-/+ signs, then sum everything up for the final answer.
 *   Each step is printed to the console so the process is easy to follow.
 * =====================================================
 */
package MidtermLabWork2.JAVA;

public class DeterminantSolver {

    // My assigned 3x3 matrix (student #14, Dupa).
    // I hardcoded it as a 2D array — each inner array represents one row.
    static int[][] matrix = {
        { 3, 2, 4 },   // row 1
        { 1, 5, 2 },   // row 2
        { 6, 3, 1 }    // row 3
    };

    // Reusable method for getting the determinant of any 2x2 matrix.
    // I need this three times during expansion, so I put it in its own method
    // instead of rewriting the formula each time.
    // a and b are the top row, c and d are the bottom row of the 2x2.
    static int computeMinor(int a, int b, int c, int d) {
        // cross-multiply diagonals and subtract: top-left×bottom-right minus top-right×bottom-left
        return (a * d) - (b * c);
    }

    // Draws the matrix to the screen with box-drawing characters on the sides.
    // I like how this looks — makes it easier to read as a proper matrix.
    static void printMatrix(int[][] m) {
        System.out.println("┌               ┐");
        for (int[] row : m) {
            System.out.printf("│  %2d  %2d  %2d  │%n", row[0], row[1], row[2]);
        }
        System.out.println("└               ┘");
    }

    // Main solver — this is where the cofactor expansion actually happens.
    // I go element by element across row 1, find the 2x2 minor for each,
    // then multiply by the element and its sign before adding it all up.
    static void solveDeterminant(int[][] m) {

        // Print the title block and show the matrix we're working with
        System.out.println("=".repeat(52));
        System.out.println("  3x3 MATRIX DETERMINANT SOLVER");
        System.out.println("  Student: DUPA, NEOMI ASHLEY SABIEN I.");
        System.out.println("  Assigned Matrix:");
        System.out.println("=".repeat(52));
        printMatrix(m);
        System.out.println("=".repeat(52));
        System.out.println();
        System.out.println("Expanding along Row 1 (cofactor expansion):");
        System.out.println();

        // M₁₁: cover up row 0 and column 0, what's left is the bottom-right 2x2
        int minor11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
        System.out.printf("  Step 1 — Minor M\u2081\u2081: det([%d,%d],[%d,%d]) = (%d\u00d7%d) - (%d\u00d7%d) = %d - %d = %d%n",
            m[1][1], m[1][2], m[2][1], m[2][2],
            m[1][1], m[2][2], m[1][2], m[2][1],
            m[1][1] * m[2][2], m[1][2] * m[2][1], minor11);

        // M₁₂: cover up row 0 and column 1, leaving columns 0 and 2
        int minor12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
        System.out.printf("  Step 2 — Minor M\u2081\u2082: det([%d,%d],[%d,%d]) = (%d\u00d7%d) - (%d\u00d7%d) = %d - %d = %d%n",
            m[1][0], m[1][2], m[2][0], m[2][2],
            m[1][0], m[2][2], m[1][2], m[2][0],
            m[1][0] * m[2][2], m[1][2] * m[2][0], minor12);

        // M₁₃: cover up row 0 and column 2, leaving only columns 0 and 1
        int minor13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);
        System.out.printf("  Step 3 — Minor M\u2081\u2083: det([%d,%d],[%d,%d]) = (%d\u00d7%d) - (%d\u00d7%d) = %d - %d = %d%n",
            m[1][0], m[1][1], m[2][0], m[2][1],
            m[1][0], m[2][1], m[1][1], m[2][0],
            m[1][0] * m[2][1], m[1][1] * m[2][0], minor13);

        // The sign pattern for first-row expansion is always +1, -1, +1.
        // I multiply each first-row element by its sign and its minor to get the cofactor.
        int c11 =  m[0][0] * minor11;
        int c12 = -m[0][1] * minor12;
        int c13 =  m[0][2] * minor13;

        System.out.println();
        System.out.printf("  Cofactor C\u2081\u2081 = (+1) \u00d7 %d \u00d7 %d = %d%n", m[0][0], minor11, c11);
        System.out.printf("  Cofactor C\u2081\u2082 = (-1) \u00d7 %d \u00d7 %d = %d%n", m[0][1], minor12, c12);
        System.out.printf("  Cofactor C\u2081\u2083 = (+1) \u00d7 %d \u00d7 %d = %d%n", m[0][2], minor13, c13);

        // Sum up the three cofactors — that's the determinant
        int det = c11 + c12 + c13;
        System.out.printf("%n  det(M) = %d + (%d) + %d%n", c11, c12, c13);
        System.out.println("=".repeat(52));
        System.out.printf("  \u2713  DETERMINANT = %d%n", det);

        // If det equals zero, the matrix is singular — it can't be inverted
        if (det == 0) {
            System.out.println("  \u26a0 The matrix is SINGULAR \u2014 it has no inverse.");
        }
        System.out.println("=".repeat(52));
    }

    // Program starts here — just calls the solver with my matrix
    public static void main(String[] args) {
        solveDeterminant(matrix);
    }

}