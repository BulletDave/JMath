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
 * Created on	: 17, December, 2014
 * Author		: David McCoy
 * Description	: A 2x2 matrix class compatible with Vec2F
 */

package com.bulletdave.math.matrix;

import com.bulletdave.math.Compute;
import com.bulletdave.math.vector.Vec2F;


public class Matrix2x2 {
	
	public static final int SIZE = 2;	// matrix size
	private float matrix[][]; 			// the 2D matrix

	/**
	 * Default constructor sets values to 0.
	 */
	public Matrix2x2() {
		matrix = new float[SIZE][SIZE];
		clearTo(0);
	}
	
	/**
	 * Constructor the matrix using individual floats
	 * @param m00	entry (0, 0)
	 * @param m01	entry (0, 1)
	 * @param m10	entry (1, 0)
	 * @param m11	entry (1, 1)
	 */
	public Matrix2x2(float m00, float m01, float m10, float m11) {
		matrix = new float[SIZE][SIZE];
		matrix[0][0] = m00;		matrix[0][1] = m01;
		matrix[1][0] = m10;		matrix[1][1] = m11;
	}
	
	/**
	 * Construct the matrix using two Vec2F
	 * @param r1	Row 1
	 * @param r2	Row 2
	 */
	public Matrix2x2(Vec2F r1, Vec2F r2) {
		matrix = new float[SIZE][SIZE];
		matrix[0][0] = r1.x;	matrix[0][1] = r1.y;
		matrix[1][0] = r2.x;	matrix[1][1] = r2.y;
	}
	
	/**
	 * Construct the matrix using an array of float
	 * @param mat	The array containing matrix entries
	 */
	public Matrix2x2(float mat[]) {
		matrix = new float[SIZE][SIZE];
		set(mat);
	}
	
	/**
	 * Construct the matrix using a 2D array of float
	 * @param mat	The 2D entry array
	 */
	public Matrix2x2(float mat[][]) {
		matrix = new float[SIZE][SIZE];
		set(mat);
	}
	
	/**
	 * Construct the matrix using an array of Vec2F
	 * @param mat	An array of 2D Vec2F
	 */
	public Matrix2x2(Vec2F mat[]) {
		matrix = new float[SIZE][SIZE];
		set(mat);
	}
	
	/**
	 * Copy constructor
	 * @param mat	The target matrix to be copied
	 */
	public Matrix2x2(Matrix2x2 mat) {
		matrix = new float[SIZE][SIZE];
		set(mat);
	}
	
	/**
	 * Set this matrix using a float array
	 * @param mat[] 	The float array containing matrix entries
	 */
	public void set(float mat[]) {
		
		// perform a deep copy of the contents of m to our matrix
		int index = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix[i][j] = mat[index];
				index++;
			}
		}
	}
	
	/**
	 * Set this matrix using a 2D float array
	 * @param mat[][] 	The 2D float array containing matrix entries
	 */
	public void set(float mat[][]) {
		
		// perform a deep copy of the contents of m to our matrix
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix[i][j] = mat[i][j];
			}
		}
	}
	
	
	/**
	 * Set this matrix using a 2D Vec2F array
	 * @param mat[]	An array of Vec2F
	 */
	public void set(Vec2F mat[]) {
		
		// perform a deep copy of the contents of m to our matrix
		for (int i = 0; i < SIZE; i++) {
			matrix[i][0] = mat[i].x;
			matrix[i][1] = mat[i].y;
		}
	}
	
	/**
	 * Set this matrix's elements to those of another Matrix
	 * @param mat	The target matrix's elements to copy over
	 */
	public void set(Matrix2x2 mat) {
		
		// perform a deep copy of the contents of m to our matrix
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix[i][j] = mat.matrix[i][j];
			}
		}
	}
	
	/**
	 * Set a target element within the matrix
	 * @param row	The element's row 
	 * @param col	The element's column
	 * @param val	The value to set the element by
	 */
	public final void set(int row, int col, float val) {
		
		if (col < 0 || col >= SIZE ||
			row < 0 || row >= SIZE) {
			return;
		}
		
		matrix[row][col] = val;
	}
	
	/**
	 * Get a particular element within the matrix
	 * @param i	The element's row
	 * @param j	The element's column
	 * @return	The value of the element at (i, j)
	 * 			0 if out of bound
	 */
	public final float get(int i, int j) {
		
		if (i < 0 || i >= SIZE || 
			j < 0 || j >= SIZE) {
			return 0;
		}
		
		return matrix[i][j];
	}
	
	/**
	 * Get a row from the matrix as a Vec2F
	 * @param i	The i'th row to fetch
	 * @return	Returns the i'th row as a Vec2F
	 */
	public final Vec2F getRow(int i) {
		
		if (i < 0 || i >= SIZE) {
			return null;
		}
		
		return new Vec2F(matrix[i][0], matrix[i][1]);
	}
	
	/**
	 * Swap a row of this matrix with another
	 * @param r1	Target row to swap
	 * @param r2	Destination row to swap
	 * @param augmented 	optional augmented matrix to swap
	 * @return	true if it succeeded,
	 * 			false if invalid row specified,
	 * 			false if no swap occurs
	 */
	private final boolean swapRow(int r1, int r2, Vec2F augmented) {
		
		if (r1 < 0 || r1 >= SIZE || 
			r2 < 0 || r2 >= SIZE || 
			r1 == r2) {
			return false;
		}
		
		for (int j = 0; j < SIZE; j++) {
			float temp = matrix[r1][j];
			matrix[r1][j] = matrix[r2][j];
			matrix[r2][j] = temp;
		}
		
		if (augmented != null) {
			float temp = augmented.x;
			augmented.x = augmented.y;
			augmented.y = temp;
		}
		
		return true;
	}

	/**
	 * Find a valid leading entry at a specified location
	 * within the matrix. Above rows are ignored.
	 * @param i	The target row
	 * @param j	The target column
	 * @param augmented 	optional augmented matrix to swap
	 * @return	true on success,
	 * 			false if no valid row found to swap with,
	 * 			false if (i, j) index is out of bound.
	 */
	private final boolean swapRowForValidPivot(int i, int j, Vec2F augmented) {
		
		if (i < 0 || j < 0 || i >= SIZE || j >= SIZE) {
			return false;
		}
		
		for (int y = i+1; y < SIZE; y++) {
			if (matrix[y][j] != 0) {
				swapRow(i, y, augmented);
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
	private final boolean swapWithValidColumnPivot(int i, int j, Matrix2x2 augmented) {
		
		if (i < 0 || j < 0 || i >= SIZE || j >= SIZE) {
			return false;
		}
		
		for (int y = i+1; y < SIZE; y++) {
			if (matrix[y][j] != 0) {
				swapRow(i, y, null);
				augmented.swapRow(i, y, null);
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
	public final Matrix2x2 transpose() {
			
		float temp = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = i+1; j < SIZE; j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		
		return this;
	}
	
	/**
	 * Add the elements of another matrix to this matrix
	 * @param m	A target matrix to add to this matrix
	 * @return	A reference of this matrix
	 */
	public final Matrix2x2 add(Matrix2x2 m) {
		
		for (int j = 0; j < SIZE; j++) {
			for (int i = 0; i < SIZE; i++) {
				matrix[i][j] += m.matrix[i][j];
			}
		}
		
		return this;
	}
	
	/**
	 * Subtract the elements of another matrix from this matrix
	 * @param m	A target matrix to negate from this matrix
	 * @return	A reference of this matrix
	 */
	public final Matrix2x2 subtract(Matrix2x2 m) {
		
		for (int j = 0; j < SIZE; j++) {
			for (int i = 0; i < SIZE; i++) {
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
	public final Matrix2x2 scale(float value) {
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix[i][j] *= value;
			}
		}
		
		return this;
	}
	
	/**
	 * Multiply a target matrix with this matrix
	 * @param m	The target matrix
	 * @return	The product of both matrices
	 */
	public final Matrix2x2 multiply(Matrix2x2 m) {
		
		float mat[][] = new float[SIZE][SIZE];

		float sum = 0;
		for (int k = 0; k < SIZE; k++) {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
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
		
		matrix = mat;
		return this;
	}
	
	// returns the product of an augmentation vector from this matrix
	public final Vec2F multiply(Vec2F augmentation) {
		
		Vec2F solution = new Vec2F();
		

		
		return solution;
	}
	
	/**
	 * The power of this matrix is the product of the multiplication 
	 * of this matrix N times.
	 * @param exponent	The amount of times to multiply this matrix by.
	 * 					Must be a positive integer or else no change is done.
	 * @return	A reference to this matrix.
	 */
	public final Matrix2x2 power(int exponent) {
		
		// can only perform a power if the matrix is square
		if (exponent <= 0) {
			return this;
		}
		
		Matrix2x2 mat = clone();
		for (int i = 1; i < exponent; i++) {
			multiply(mat);
		}
		
		return this;
	}
	
	/**
	 * Solve this matrix using Gaussian Elimination.
	 * This can be used to solve for linear equations.
	 * Once solved, this matrix should become an identity matrix,
	 * otherwise, if it is not an identity matrix, it is not possible
	 * to fully reduce this set of linear equations.
	 * @param x	augmented column x value
	 * @param y augmented column y value
	 * @return	an solved augmented Vec2F
	 */
	public final Vec2F solve(float x, float y) {
		
		int j = 0;
		float entry = 0;
		Vec2F augmented = new Vec2F(x, y);
		
		for (int i = 0; i < SIZE; i++) {
			
			// 1. set to valid leading column entry
			if (matrix[i][j] == 0) {
				if (!swapRowForValidPivot(i, j, augmented)) {
					j++;
					continue;
				}
			}
				
			// 2. divide the row to make the leading entry 1
			entry = matrix[i][j];
			if (entry != 1) {
				
				if (i == 0) 		augmented.x /= entry;
				else if (i == 1) 	augmented.y /= entry;
				
				for (int l = 0; l < SIZE; l++) {
					
					matrix[i][l] /= entry;
					
					if (matrix[i][l] == -0.0f) {
						matrix[i][l] = 0.0f;
					}
				}
			}
			
			// 3. eliminate all other entries
			for (int k = 0; k < SIZE; k++) {
				
				entry = matrix[k][j];				
				if (k != i) {
					
					if (k == 0) 		augmented.x -= augmented.y * entry;
					else if (k == 1) 	augmented.y -= augmented.x * entry;
					
					for (int l = 0; l < SIZE; l++) {
						
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
		
		return augmented;
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
	public final Matrix2x2 inverse() {

		Matrix2x2 invMat = clone();
		Matrix2x2 augmented = new Matrix2x2();
		
		for (int i = 0; i < SIZE; i++) {
			augmented.matrix[i][i] = 1;
		}
		
		float entry = 0;
		int j = 0;
		
		for (int i = 0; i < SIZE; i++) {
			
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
				
				for (int l = 0; l < SIZE; l++) {
					
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
			for (int k = 0; k < SIZE; k++) {
				
				entry = invMat.matrix[k][j];
				if (k != i) {
					for (int l = 0; l < SIZE; l++) {
						
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
		
		for (int i = 0; i < SIZE; i++) {
			for (j = 0;  j < SIZE; j++) {
				matrix[i][j] = augmented.matrix[i][j];
			}
		}
		
		return this;
	}
	
	/**
	 * Clear all matrix elements to a specified value
	 * @param val 	The value to set this matrix's elements to
	 */
	public final void clearTo(float val) {
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				matrix[i][j] = val;
			}
		}
	}
	
	/**
	 * Verify if this matrix is a zero matrix.
	 * @return	true if all elements are zero.
	 * 			false if a single element is not zero.
	 */
	public final boolean isZeroMatrix() {
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (matrix[i][j] != 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Verify if this matrix is a lower triangle matrix.
	 * Top-right entries are zero, excluding diagonal.
	 * @return	true if it is a lower triangle matrix.
	 */
	public final boolean isLowerTriangleMatrix() {
		
		int size = SIZE - 1;
		for (int i = 0; i < size; i++ ) {
			for (int j = i+1; j < SIZE; j++) {
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
		
		for (int i = 1; i < SIZE; i++) {
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
		
		int size = SIZE - 1;
		for (int i = 0; i < size; i++ ) {
			for (int j = i+1; j < SIZE; j++) {
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
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				
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
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				
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
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Matrix2x2)) {
			return false;
		}
		
		Matrix2x2 m = (Matrix2x2) obj;
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
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
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				strMatrix += matrix[i][j] + " ";
			}
			strMatrix += "\n";
		}
		
		return strMatrix;
	}
	
	@Override
	public Matrix2x2 clone() {
		return new Matrix2x2(this);
	}
}
