/**
 * =====================================================
 * Student Name    : DUPA, NEOMI ASHLEY SABIEN I.
 * Course          : Math 101 — Linear Algebra
 * Assignment      : Programming Assignment 1 — 3x3 Matrix Determinant Solver
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : March 16, 2025
 * GitHub Repo     : https://github.com/neomiashley/Prog2-9307-AY225-DUPA.git
 * Runtime         : Node.js (run with: node determinant_solver.js)
 *
 * Description:
 *   This is the JavaScript version of my determinant solver — same logic
 *   as the Java file but written for Node.js. I expand along the first row,
 *   compute three 2x2 minors, attach the +/-/+ signs, and sum the results.
 *   Every calculation is printed out so the full solution is visible.
 * =====================================================
 */

// My assigned matrix as a 2D JS array (student #14).
// I keep all three rows here — the outer array is the matrix,
// and each inner array holds one row's values.
const matrix = [
    [3, 2, 4],   // row 1
    [1, 5, 2],   // row 2
    [6, 3, 1]    // row 3
];

// Prints the matrix with bracket borders so it looks like a real matrix.
// I loop through each row and pad the numbers so columns line up nicely.
function printMatrix(m) {
    console.log(`┌               ┐`);
    m.forEach(row => {
        const fmt = row.map(v => v.toString().padStart(3)).join("  ");
        console.log(`│ ${fmt}  │`);
    });
    console.log(`└               ┘`);
}

// Computes the determinant of a 2x2 matrix given its four values.
// I pass in a, b for the top row and c, d for the bottom row.
// The formula is just ad minus bc.
function computeMinor(a, b, c, d) {
    return (a * d) - (b * c);
}

// This function runs the full cofactor expansion.
// I print each minor computation individually so every step is visible,
// then show the cofactor terms and the final sum.
function solveDeterminant(m) {
    const line = "=".repeat(52);

    // Display the header and the matrix before doing any math
    console.log(line);
    console.log("  3x3 MATRIX DETERMINANT SOLVER");
    console.log("  Student: DUPA, NEOMI ASHLEY SABIEN I.");
    console.log("  Assigned Matrix:");
    console.log(line);
    printMatrix(m);
    console.log(line);
    console.log();
    console.log("Expanding along Row 1 (cofactor expansion):");
    console.log();

    // M₁₁: block out row 0 and column 0, the leftover 2x2 uses indices [1][1],[1][2],[2][1],[2][2]
    const minor11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
    console.log(
        `  Step 1 — Minor M₁₁: det([${m[1][1]},${m[1][2]}],[${m[2][1]},${m[2][2]}])` +
        ` = (${m[1][1]}×${m[2][2]}) - (${m[1][2]}×${m[2][1]}) = ${m[1][1]*m[2][2]} - ${m[1][2]*m[2][1]} = ${minor11}`
    );

    // M₁₂: block out row 0 and column 1, use columns 0 and 2 from rows 1 and 2
    const minor12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
    console.log(
        `  Step 2 — Minor M₁₂: det([${m[1][0]},${m[1][2]}],[${m[2][0]},${m[2][2]}])` +
        ` = (${m[1][0]}×${m[2][2]}) - (${m[1][2]}×${m[2][0]}) = ${m[1][0]*m[2][2]} - ${m[1][2]*m[2][0]} = ${minor12}`
    );

    // M₁₃: block out row 0 and column 2, only columns 0 and 1 remain
    const minor13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);
    console.log(
        `  Step 3 — Minor M₁₃: det([${m[1][0]},${m[1][1]}],[${m[2][0]},${m[2][1]}])` +
        ` = (${m[1][0]}×${m[2][1]}) - (${m[1][1]}×${m[2][0]}) = ${m[1][0]*m[2][1]} - ${m[1][1]*m[2][0]} = ${minor13}`
    );

    // Attach the +1/-1/+1 signs and multiply by the corresponding first-row element
    const c11 =  m[0][0] * minor11;
    const c12 = -m[0][1] * minor12;
    const c13 =  m[0][2] * minor13;

    console.log();
    console.log(`  Cofactor C₁₁ = (+1) × ${m[0][0]} × ${minor11} = ${c11}`);
    console.log(`  Cofactor C₁₂ = (-1) × ${m[0][1]} × ${minor12} = ${c12}`);
    console.log(`  Cofactor C₁₃ = (+1) × ${m[0][2]} × ${minor13} = ${c13}`);

    // Add all three cofactors together — this is the determinant
    const det = c11 + c12 + c13;
    console.log();
    console.log(`  det(M) = ${c11} + (${c12}) + ${c13}`);
    console.log(line);
    console.log(`  ✓  DETERMINANT = ${det}`);

    // If the determinant is 0, the matrix has no inverse and we flag it
    if (det === 0) {
        console.log("  ⚠ The matrix is SINGULAR — it has no inverse.");
    }
    console.log(line);
}

// Run the solver — this is where execution starts
solveDeterminant(matrix);