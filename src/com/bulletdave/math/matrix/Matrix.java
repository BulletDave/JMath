/*
 *	Copyright (c) 2016, David McCoy
 *	All rights reserved.
 *	License: (BSD)
 *	
 *	Redistribution and use in source and binary forms, with or without
 *	modification, are permitted provided that the following conditions are met:
 *	1. Redistributions of source code must retain the above copyright
 *	   notice, this list of conditions and the following disclaimer.
 *	2. Redistributions in binary form must reproduce the above copyright
 *	   notice, this list of conditions and the following disclaimer in the
 *	   documentation and/or other materials provided with the distribution.
 *	3. All advertising materials mentioning features or use of this software
 *	   must display the following acknowledgement: David McCoy
 *	   This product includes software developed by the Oracle Corporation
 *	4. Neither the name of the David McCoy nor the
 *	   names of its contributors may be used to endorse or promote products
 *	   derived from this software without specific prior written permission.
 *	
 *	THIS SOFTWARE IS PROVIDED BY David McCoy ''AS IS'' AND ANY
 *	EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *	WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *	DISCLAIMED. IN NO EVENT SHALL David McCoy BE LIABLE FOR ANY
 *	DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *	(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *	LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *	ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *	(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *	SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Copyright 2014 (C)
 * 
 * Created on	: 15, December, 2014
 * Author		: David McCoy
 * Description	: A generic matrix class
 */

package com.bulletdave.math.matrix;

import com.bulletdave.math.Compute;


public class Matrix {
	
	private int rowCount;				// vertical size
	private int colCount;				// horizontal size
	private float matrix[][];			// the 2D matrix

	/**
	 * Default constructor sets values to 0.
	 * The matrix itself is null as dimension
	 * is undetermined.
	 */
	public Matrix() {
		rowCount = 0;
		colCount = 0;
		matrix = null;
	}
	
	/**
	 * Construct a Matrix with a float array
	 * @param mat	The float array containing matrix entries
	 * @param rCount	The row count of the array
	 * @param cCount	The column count of the array
	 */
	public Matrix(float mat[], int rCount, int cCount) {
		rowCount = 0;
		colCount = 0;
		matrix = null;
		set(mat, rCount, cCount);
	}
	
	/**
	 * Construct a Matrix with desired dimension and fill with zeros
	 * @param rCount	Matrix row count
	 * @param cCount	Matrix column count
	 */
	public Matrix(int rCount, int cCount) {
		
		rowCount = rCount;
		colCount = cCount;
		matrix = new float[rCount][cCount];
		
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				matrix[i][j] = 0;
			}
		}
	}
	
	/**
	 * Copy constructor
	 * @param m	Target matrix to perform a deep copy of
	 */
	public Matrix(Matrix m) {
		
		rowCount = m.rowCount;
		colCount = m.colCount;
		
		// do not perform a deep copy if matrix m is not yet initialized
		if (m.matrix == null) {
			matrix = null;
			return;
		}
		
		matrix = new float[rowCount][colCount];
		
		// perform the deep copy
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				matrix[i][j] = m.matrix[i][j];
			}
		}
	}
	
	/**
	 * Set this matrix using a float array
	 * @param mat	The float array containing matrix entries
	 * @param rCount	Matrix row count
	 * @param cCount	Matrix column count
	 * @return	true if it succeeded, 
	 * 			false if incompatible matrix dimension
	 */
	public boolean set(float mat[], int rCount, int cCount) {
		
		// allocated and set the matrix data if it was not yet set
		if (matrix == null) {
			rowCount = rCount;
			colCount = cCount;
			matrix = new float[rCount][cCount];
		}
	
		// cannot set a matrix with one with a different size
		if (rowCount != rCount || colCount != cCount) {
			return false;
		}
		
		// perform a deep copy of the contents of m to our matrix
		int index = 0;
		for (int i = 0; i < rCount; i++) {
			for (int j = 0; j < cCount; j++) {
				matrix[i][j] = mat[index];
				index++;
			}
		}
		
		return true;
	}
	
	/**
	 * Set a target element within the matrix
	 * @param row	The element's row 
	 * @param col	The element's column
	 * @param val	The value to set the element by
	 */
	public final void set(int row, int col, float val) {
		matrix[row][col] = val;
	}
	
	/**
	 * Get a particular element within the matrix
	 * @param i	The element's row
	 * @param j	The element's column
	 * @return	The value of the element at (i, j)
	 * 			0 if out of bound
	 * 			0 if matrix was not yet initialized
	 */
	public final float get(int i, int j) {
		
		// return 0 if we are out of bound
		// return 0 if matrix is not initialized
		if (i < 0 || j < 0 ||
			i >= rowCount || 
			j >= colCount ||
			matrix == null) {
			return 0;
		}
		
		return matrix[i][j];
	}
	
	/**
	 * Get the row count of this matrix
	 * @return	The row count
	 */
	public final int getRowCount() {
		return rowCount;
	}
	
	/**
	 * Get the column count of this matrix
	 * @return	The column count
	 */
	public final int getColCount() {
		return colCount;
	}
	
	/**
	 * Swap a row of this matrix with another
	 * @param r1	Target row to swap
	 * @param r2	Destination row to swap
	 * @return	true if it succeeded,
	 * 			false if invalid row specified,
	 * 			false if no swap occurs
	 */
	private final boolean swapRow(int r1, int r2) {
		
		if (r1 < 0 || r2 < 0 ||	
			r1 >= rowCount || r2 >= rowCount || 
			r1 == r2) {
			return false;
		}
		
		for (int j = 0; j < colCount; j++) {
			float temp = matrix[r1][j];
			matrix[r1][j] = matrix[r2][j];
			matrix[r2][j] = temp;
		}
		
		return true;
	}

	/**
	 * Find a valid leading entry at a specified location
	 * within the matrix. Above rows are ignored.
	 * @param i	The target row
	 * @param j	The target column
	 * @return	true on success,
	 * 			false if no valid row found to swap with,
	 * 			false if (i, j) index is out of bound.
	 */
	private final boolean swapRowForValidPivot(int i, int j) {
		
		if (i < 0 || j < 0 || i >= rowCount || j >= rowCount) {
			return false;
		}
		
		for (int y = i+1; y < rowCount; y++) {
			if (matrix[y][j] != 0) {
				swapRow(i, y);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Find a valid leading entry at a specified location
	 * within the matrix. Above rows are ignored. Performs
	 * the same row swap for an augmented matrix
	 * @param i	The target row
	 * @param j	The target column
	 * @param augmented	An augmented matrix
	 * @return	true on success,
	 * 			false if no valid row found to swap with,
	 * 			false if (i, j) index is out of bound.
	 */
	private final boolean swapWithValidColumnPivot(int i, int j, Matrix augmented) {
		
		if (i < 0 || j < 0 || i >= rowCount || j >= rowCount) {
			return false;
		}
		
		for (int y = i+1; y < rowCount; y++) {
			if (matrix[y][j] != 0) {
				swapRow(i, y);
				augmented.swapRow(i, y);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Transpose the matrix by flipping all elements 
	 * along the diagonal.
	 * @return	A reference to this matrix
	 */
	public final Matrix transpose() {
		
		int newRowCount = colCount;
		int newColCount = rowCount;
		
		if (isSquareMatrix()) {
			
			float temp = 0;
			for (int i = 0; i < rowCount; i++) {
				for (int j = i+1; j < colCount; j++) {
					temp = matrix[i][j];
					matrix[i][j] = matrix[j][i];
					matrix[j][i] = temp;
				}
			}
			
		} else {
			
			float mat[][] = new float[newRowCount][newColCount];
			
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < colCount; j++) {
					mat[j][i] = matrix[i][j];
				}
			}
			
			matrix = mat;
		}
		
		colCount = newColCount;
		rowCount = newRowCount;
		
		return this;
	}
	
	/**
	 * Add the elements of another matrix to this matrix
	 * @param m	A target matrix to add to this matrix
	 * @return	A reference of this matrix
	 * 			No change occurs if the supplied matrix
	 * 			has a dimension mismatch
	 */
	public final Matrix add(Matrix m) {
		
		// cannot add a matrix with a different size
		if (rowCount != m.rowCount ||
			colCount != m.colCount) {
			return this;
		}
		
		for (int j = 0; j < colCount; j++) {
			for (int i = 0; i < rowCount; i++) {
				matrix[i][j] += m.matrix[i][j];
			}
		}
		
		return this;
	}
	
	/**
	 * Subtract the elements on another matrix from this matrix
	 * @param m	A target matrix to negate from this matrix
	 * @return	A reference of this matrix
	 * 			No change occurs if the supplied matrix
	 * 			has a dimension mismatch
	 */
	public final Matrix subtract(Matrix m) {
		
		// cannot subtract a matrix with a different size
		if (rowCount != m.rowCount ||
			colCount != m.colCount) {
			return this;
		}
		
		for (int j = 0; j < colCount; j++) {
			for (int i = 0; i < rowCount; i++) {
				matrix[i][j] -= m.matrix[i][j];
			}
		}
		
		return this;
	}
	
	/**
	 * Scale this matrix my multiplying each of its 
	 * elements by a given value
	 * @param value	The value to scale this matrix by
	 * @return	A reference to this matrix
	 */
	public final Matrix scale(float value) {
		
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				matrix[i][j] *= value;
			}
		}
		
		return this;
	}
	
	/**
	 * Multiply a target matrix with this matrix
	 * @param m	The target matrix
	 * @return	The product of both matrices (can be different in dimension)
	 */
	public final Matrix multiply(Matrix m) {
		
		if (!canMultiply(m)) {
			return this.clone();
		}
		
		float mat[][] = new float[rowCount][m.colCount];

		float sum = 0;
		for (int k = 0; k < m.colCount; k++) {
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < colCount; j++) {
					sum += matrix[i][j] * m.matrix[j][k];
				}
				
				float decVal = sum < 0 ? -sum : sum;
				decVal = decVal - (int)decVal;
				if (decVal <= Compute.ERROR || (decVal+Compute.ERROR) >= 1.0f) {
					sum = Math.round(sum);
				}
				
				mat[i][k] = sum;
				sum = 0;
			}
		}
		
		colCount = m.colCount;	
		matrix = mat;
		return this;
	}
	
	/**
	 * The power of this matrix is the product of the multiplication 
	 * of this matrix N times. The matrix must be square for this to work,
	 * otherwise, nothing will be done.
	 * @param exponent	The amount of times to multiply this matrix by.
	 * 					Must be a positive integer or else no change is done.
	 * @return	A reference to this matrix.
	 */
	public final Matrix power(int exponent) {
		
		// can only perform a power if the matrix is square
		if (rowCount != colCount && exponent <= 0) {
			return this;
		}
		
		Matrix mat = clone();
		for (int i = 1; i < exponent; i++) {
			multiply(mat);
		}
		
		return this;
	}
	
	/**
	 * Solve this matrix using Gaussian Elimination.
	 * This can be used to solve for linear equations.
	 * An extra column can be added for the result.
	 * Once solved, this matrix should become an identity matrix,
	 * otherwise, if it is not an identity matrix, it is not possible
	 * to fully reduce this set of linear equations.
	 * @return	A reference to this matrix.
	 */
	public final Matrix solve() {
		
		int j = 0;
		float entry = 0;
		
		for (int i = 0; i < rowCount; i++) {
			
			// 1. set to valid leading column entry
			if (matrix[i][j] == 0) {
				if (!swapRowForValidPivot(i, j)) {
					j++;
					continue;
				}
			}
				
			// 2. divide the row to make the leading entry 1
			entry = matrix[i][j];
			if (entry != 1) {
				
				for (int l = 0; l < colCount; l++) {
					matrix[i][l] /= entry;
					if (matrix[i][l] == -0.0f) {
						matrix[i][l] = 0.0f;
					}
				}
			}
			
			// 3. eliminate all other entries
			for (int k = 0; k < rowCount; k++) {
				
				entry = matrix[k][j];				
				if (k != i) {
					for (int l = 0; l < colCount; l++) {
						matrix[k][l] -= matrix[i][l] * entry;
						if (matrix[k][l] == -0.0f) {
							matrix[k][l] = 0.0f;
						}
					}
				}
			}
			
			// 4. increase i and j by 1 for new pivot point
			j++;
		}
		
		return this;
	}
	
	/**
	 * Compute the inverse of this matrix using Gaussian Elimination,
	 * but with an augmented matrix alongside it starting off as
	 * an identity matrix. This can be used to solve for vectors with 
	 * a matrix multiplication. The benefits are improved efficiency.
	 * @return	A reference to this matrix.
	 * 			If the matrix was unable to be fully reduced (e.g.
	 * 			was unable to reduce matrix into an identity matrix),
	 * 			the original matrix is reverted and no change occurs.
	 */
	public final Matrix inverse() {
		
		if (!isSquareMatrix()) {
			return this;
		}

		Matrix invMat = clone();
		Matrix augmented = new Matrix(rowCount, colCount);
		
		for (int i = 0; i < rowCount; i++) {
			augmented.matrix[i][i] = 1;
		}
		
		float entry = 0;
		int j = 0;
		
		for (int i = 0; i < rowCount; i++) {
			
			// 1. set to valid leading column entry
			if (invMat.matrix[i][j] == 0) {
				
				if (!invMat.swapWithValidColumnPivot(i, j, augmented)) {
					j++;
					continue;
				}
			}
				
			// 2. divide the row to make the leading entry 1
			entry = invMat.matrix[i][j];
			if (entry != 1) {
				
				for (int l = 0; l < colCount; l++) {
					
					invMat.matrix[i][l] /= entry;
					augmented.matrix[i][l] /= entry;
					
					if (invMat.matrix[i][l] == -0.0f) {
						invMat.matrix[i][l] = 0.0f;
					}
					
					if (augmented.matrix[i][l] == -0.0f) {
						augmented.matrix[i][l] = 0.0f;
					}
				}
			}
			
			// 3. eliminate all other entries
			for (int k = 0; k < rowCount; k++) {
				
				entry = invMat.matrix[k][j];
				if (k != i) {
					for (int l = 0; l < colCount; l++) {
						
						invMat.matrix[k][l] -= invMat.matrix[i][l] * entry;
						augmented.matrix[k][l] -= augmented.matrix[i][l] * entry;
						
						if (invMat.matrix[k][l] == -0.0f) {
							invMat.matrix[k][l] = 0.0f;
						}
						
						if (augmented.matrix[k][l] == -0.0f) {
							augmented.matrix[k][l] = 0.0f;
						}
					}
				}
			}
			
			// 4. increase i and j by 1 for new pivot point
			j++;
		}
		
		// if the solved matrix is not an identity matrix at the end,
		// no inverse matrix exists, so the matrix does not change.
		if (!invMat.isIdentityMatrix()) {
			return this;
		}
		
		for (int i = 0; i < rowCount; i++) {
			for (j = 0;  j < colCount; j++) {
				matrix[i][j] = augmented.matrix[i][j];
			}
		}
		
		return this;
	}
	
	/**
	 * Verify if this matrix is able to be 
	 * multiplied with a target matrix.
	 * @param m	The target matrix
	 * @return	true if compatible for multiplication
	 * 			false if incompatible for multiplication
	 */
	public final boolean canMultiply(Matrix m) {
		
		int n1 = colCount;
		int n2 = m.rowCount;
		return n1 == n2;
	}
	
	/**
	 * Verify if this matrix is a zero matrix.
	 * @return	true if all elements are zero.
	 * 			false if a single element is not zero.
	 */
	public final boolean isZeroMatrix() {
		
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				if (matrix[i][j] != 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Verify if this is a square matrix
	 * @return	true if the width of this matrix equals
	 * 			the height of this matrix, false otherwise.
	 */
	public final boolean isSquareMatrix() {
		return colCount == rowCount;
	}
	
	/**
	 * Verify if this matrix is a lower triangle matrix.
	 * Top-right entries are zero, excluding diagonal.
	 * @return	true if it is a lower triangle matrix.
	 */
	public final boolean isLowerTriangleMatrix() {
		
		int size = rowCount - 1;
		for (int i = 0; i < size; i++ ) {
			for (int j = i+1; j < colCount; j++) {
				if (matrix[i][j] != 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Verify if this matrix is an upper triangle matrix.
	 * Bottom-left entries are zero, excluding diagonal.
	 * @return	true if it is an upper triangle matrix.
	 */
	public final boolean isUpperTriangleMatrix() {
		
		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < i; j++) {
				if (matrix[i][j] != 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Verify if this matrix is symmetric along the diagonal.
	 * @return	true if the matrix is symmetric along the diagonal.
	 */
	public final boolean isSymmetricMatrix() {
		
		int size = rowCount - 1;
		for (int i = 0; i < size; i++ ) {
			for (int j = i+1; j < colCount; j++) {
				if (matrix[i][j] != matrix[j][i]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Verify if this matrix is diagonal. This happens
	 * when the matrix is a zero matrix, with the exception
	 * of the diagonal.
	 * @return	true if this is a diagonal matrix,
	 * 			false if this is a zero matrix
	 * 			false otherwise
	 */
	public final boolean isDiagonalMatrix() {
		
		if (isZeroMatrix()) {
			return false;
		}
		
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				
				if (i != j) {
					if (matrix[i][j] != 0) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Verify if this is an identity matrix where
	 * all of its elements are zero but the diagonal
	 * which are set to 1.
	 * @return	true if this is an identity matrix.
	 */
	public final boolean isIdentityMatrix() {
		
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				
				if (i == j) {
					if (matrix[i][j] != 1) {
						return false;
					}
				} else {
					if (matrix[i][j] != 0) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Verify if this matrix is a row vector (nx1)
	 * @return	true if this matrix dimension is (nx1)
	 */
	public final boolean isRowVector() {
		return rowCount == 1;
	}
	
	/**
	 * Verify if this matrix is a row vector (1xn)
	 * @return	true if this matrix dimension is (1xn)
	 */
	public final boolean isColumnVector() {
		return colCount == 1;
	}
	
	/**
	 * Verify if two values are identical given a threshold
	 * @param a	A value to test
	 * @param b	A value to test
	 * @param threshold	The tolerance between value 'a' and 'b'
	 * @return	true if the difference between 'a' and 'b' is less than the threshold
	 */
	private final boolean equals(float a, float b, float threashold) {
		
		float diff = a - b;
		diff = diff < 0 ? -diff : diff;
		
		if (diff > threashold) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Matrix)) {
			return false;
		}
		
		Matrix m = (Matrix) o;
		if (m.rowCount != rowCount || m.colCount != colCount) {
			return false;
		}
		
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				if (equals(m.matrix[i][j], matrix[i][j], Compute.ERROR)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		
		String strMatrix = new String();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				strMatrix += matrix[i][j] + " ";
			}
			strMatrix += "\n";
		}
		
		return strMatrix;
	}
	
	@Override
	public Matrix clone() {
		return new Matrix(this);
	}
}
