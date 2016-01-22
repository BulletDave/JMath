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

/*==================================================================
 * Project Name: Physics
 * 
 * Project Purpose: A personal generic API for future projects.
 * 
 * Created By: David McCoy,            	<06/19/12>
 * Modified By: David McCoy,           	<06/20/12>
 * Modified By: David McCoy, 			<12/18/12>
 * Modified By: David McCoy, 			<12/17/14>
 * 
 *=================================================================*/

package com.bulletdave.math;

import java.util.Random;

import com.bulletdave.math.vector.Vec2F;

public class Compute {
	
	private static final Random RAND 		= new Random();
	
	public static final float PI  			= 3.141592653589793f;
	public static final float TWO_PI 		= 6.283185307179586f;
	public static final float HALF_PI 		= 1.570796326794897f;
	public static final float INVERSE_PI 	= 0.318309886192889f;
	public static final float DEG_TO_RAD	= 0.017453292519943f;
	public static final float RAD_TO_DEG	= 57.29577951308232f;
	public static final float ERROR 		= 2.0e-5f;
	public static final float PRECISION_LOSS = 2.0e-5f;
	// ERROR is the accountable computational loss on float from math operations
	
	/**
	 * Set the seed of the randomizer
	 * @param seed	the randomizer seed
	 */
	public static final void srand(long seed) {
		RAND.setSeed(seed);
	}
	
	/**
	 * Generate a positive random integer between [0, max-1]
	 * @param max	The range for random values.
	 * @return	returns a positive random integer between [0, max-1]
	 */
	public static final int random(int max) {
		return RAND.nextInt(max);
	}
	
	/**
	 * Generate a random integer between within a set range [min, max-1]
	 * @param min	The lower bound range, can be negative
	 * @param max	The upper bound range, can also be negative
	 * @return	returns a random integer between [min, max-1]
	 */
	public static final int random(int min, int max) {

		int absRange = abs(max - min);
		int val = RAND.nextInt(absRange);
		
		return (min + val);
	}
	
	/**
	 * Test for equality between floats given a threshold.
	 * @param a	A value to compare
	 * @param b	A value to compare
	 * @param threshold	The tolerance between value 'a' and 'b'
	 * @return	True if the difference between 'a' and 'b' is less than the threshold
	 */
	public static final boolean equals(float a, float b, float threshold) {

		return ((a >= b - PRECISION_LOSS - threshold) && 
				(a <= b + PRECISION_LOSS + threshold));
	}
	
	/**
	 * Test for equality between floats
	 * @param a	A value to compare
	 * @param b	A value to compare
	 * @return	True if 'a' and 'b' are similar
	 */
	public static final boolean equals(float a, float b) {
		
		return a >= (b - PRECISION_LOSS) && 
			   a <= (b + PRECISION_LOSS);
	}
	
	/**
	 * Get the minimum value between two values
	 * @param a	A value to compare
	 * @param b	A value to compare
	 * @return	The smallest value between 'a' or 'b'
	 */
	public static final float min(float a, float b) {
		
		if (a > b) {
			return b;
		} else {
			return a;
		}
	}
	
	/**
	 * Get the maximum value between two values
	 * @param a	A value to compare
	 * @param b	A value to compare
	 * @return	The largest value between 'a' or 'b'
	 */
	public static final float max(float a, float b) {
		
		if (a >= b) {
			return a;
		} else {
			return b;
		}
	}
	
	/**
	 * Get the absolute value of a float
	 * @param a	The target value
	 * @return	The absolute value of 'a'
	 */
	public static final float abs(float a) {
		
		if (a < 0.0f) {
			return -a;
		}
		
		return a;
	}
	
	/**
	 * Get the absolute value of a double
	 * @param a	The target value
	 * @return	The absolute value of 'a'
	 */
	public static final double abs(double a) {
		// return the absolute value of a double, negative -> positive
		// about 2.4 times faster then Math.abs()
		
		if (a <= -0.0d) {
			return -a;
		}
		
		return a;
	}
	
	/**
	 * Get the absolute value of an integer
	 * @param a	The target value
	 * @return	The absolute value of 'a'
	 */
	public static final int abs(int a) {
		
		if (a <= -0) return -a;		
		return a;
	}
	
	/**
	 * Round down a float to the closest integer
	 * @param a	A value to round down
	 * @return	The rounded-down value of 'a'
	 */
	public static final int floor(float a) {
		
		if (a >= 0) return (int)a;
		return (int)a-1;
	}
	
	/**
	 * Round up a float to the closest integer
	 * @param a	A value to round up
	 * @return	The rounded-up value of 'a'
	 */
	public static final int ceil(float a) {
		
		if (a <= 0) return (int)a;
		return (int)a+1;
	}
	
	/**
	 * Round a float to the closest integer
	 * @param a	A value to round
	 * @return	The rounded value of 'a'
	 */
	public static final int round(float a) {
		
		float b = 0;
		if (a >= 0) {
			b = a - (int)a;
			if (b >= 0.5f) return ceil(a);
			return floor(a);
		}
		
		b = a + (int)abs(a);
		if (b >= -0.5f) return ceil(a);
		return floor(a);
	}
	
	/**
	 * Get the sign of a float
	 * @param a	A value to verify its sign
	 * @return	The sign of the float 'a' {-1, 0, 1}
	 */
	public static final float signum(float a) {
		
		if (a < 0.0f) {
			return -1.0f;
		} else if (a > 0.0f) {
			return 1.0f;
		}

		return 0.0f;
	}
	
	/**
	 * Get the sign of a double
	 * @param a	A value to verify its sign
	 * @return	The sign of the double 'a' {-1, 0, 1}
	 */
	public static final double signum(double a) {
		
		if (a < 0.0d) {
			return -1.0d;
		} else if (a > 0) {
			return 1.0d;
		}
		
		return 0.0f;
	}
	
	/**
	 * Limit the range of an integer value to [min, max]
	 * @param val	The value to be clamped
	 * @param min	The lower bound limit
	 * @param max	The upper bound limit
	 * @return	The clamped value with respect to [min, max]
	 */
	public static final int clamp(int val, int min, int max) {
		
		if (val < min) {
			val = min;
		} else if (val > max) {
			val = max;
		}
		
		return val;
	}
	
	/**
	 * Limit the range of a float value to [min, max]
	 * @param val	The value to be clamped
	 * @param min	The lower bound limit
	 * @param max	The upper bound limit
	 * @return	The clamped value with respect to [min, max]
	 */
	public static final float clamp(float val, float min, float max) {
		
		if (val < min) {
			val = min;
		} else if (val > max) {
			val = max;
		}
		
		return val;
	}
	
	/**
	 * Limit the range of a double value to [min, max]
	 * @param val	The value to be clamped
	 * @param min	The lower bound limit
	 * @param max	The upper bound limit
	 * @return	The clamped value with respect to [min, max]
	 */
	public static final double clamp(double val, double min, double max) {
		// set bounds of [min - max] to a double
		
		if (val <= min) {
			val = min;
		} else if (val > max) {
			val = max;
		}
		
		return val;
	}
	
	/**
	 * Convert radians into degree
	 * @param radians	The target radians to be converted
	 * @return	The degree
	 */
	public static final float radToDeg(float radians) {
		// deg = (rad/2PI)*360
		// deg = rad(2PI/360)
		// (2PI/360) = 57.29577951308232
		
		return radians * RAD_TO_DEG;
	}
	
	/**
	 * Convert degree into radians
	 * @param degrees	The target degree to be converted
	 * @return	The radian
	 */
	public static final float degToRad(float degrees) {
		// rad = (deg*2PI)/360
		// multiplication is faster then division
		// 2PI/360 = 0.017453292519943
		
		return degrees * DEG_TO_RAD;
	}
	
	/**
	 * Converts a 2D point into radians with respect to the 
	 * origin (0, 0) in a cartesian plane.
	 * @param x	The x-location
	 * @param y	The y-location
	 * @return	The radians [0, 2PI]
	 * 			For example:
	 * 			(1, 0) = 0  radian
	 * 			(0, 1) = PI/2 radians
	 * 			(-1, 0) = PI radians
	 */
	public static final float slopeToRad(float x, float y) {

		if (x == 0.0f && y == 0.0f) {
			return 0.0f;
		}
		
		float radians = (float) Math.atan2(y, x);
		
		if (radians == -0.0f) {
			return 0.0f;
		}
		
		if (radians < 0.0f) {
			return (float) (TWO_PI + radians);
		}
		
		return radians;
	}
	
	/**
	 * Determines which side a point is on with respect to a line.
	 * @param point	The point to test
	 * @param start	The beginning of the line
	 * @param end	The end of the line
	 * @return Positive value if the point is to the right or bellow a line,
	 * 		   with respect to the direction of the line. Can use this for
	 * 		   convex polygon collision detection using clock-wise wounding rule.
	 */
	public static final float sidePointOn(Vec2F point, Vec2F start, Vec2F end) {
		
		Vec2F pointDiff = point.clone().negate(end);
		Vec2F startDiff = start.clone().negate(end);
		
		return startDiff.getCrossProduct(pointDiff);
	}
	
	/**
	 * Test if a point is inside a triangle
	 * @param point	The point to test
	 * @param a	Triangle vertex A
	 * @param b	Triangle vertex B
	 * @param c	Triangle vertex C
	 * @return	true is the point is within the triangle
	 */
	public static final boolean testPointInsideTriangle(Vec2F point, Vec2F a, Vec2F b, Vec2F c) {
		
		boolean b1 = Compute.sidePointOn(point, a, b) > 0.0f;
		boolean b2 = Compute.sidePointOn(point, b, c) > 0.0f;
		boolean b3 = Compute.sidePointOn(point, c, a) > 0.0f;
		
		return (b1 == b2 && b2 == b3);
	}
	
	/**
	 * Test if a point is inside a circle
	 * @param x	The x-location to test
	 * @param y	The y-location to test
	 * @param cx	The circle's center x-location
	 * @param cy	The circle's center y-location
	 * @param cr	The circle's radius
	 * @return	true if the point is within the circle
	 */
	public static final boolean testPointInsideCircle(float x, float y, float cx, float cy, float cr) {
		// ((x - cx)^2 + (y - cy)^2) < (radius^2)
		
		float xx 			= x - cx;
		float yy 			= y - cy;
		float distance 		= (float) (xx*xx + yy*yy);
		float radius 		= (float) (cr*cr);
		return distance < radius;
	}
	
	/**
	 * Test if a point is inside an ellipse
	 * @param x	The x-location to test
	 * @param y	The y-location to test
	 * @param cx	The ellipse's center x-location
	 * @param cy	The ellipse's center y-location
	 * @param rw	The ellipse's width
	 * @param rh	The ellipse's height
	 * @return	true if the point is within the circle
	 */
	public static final boolean testPointInsideEllipse(float x, float y, float cx, float cy, float rw, float rh) {
		// http://mathforum.org/library/drmath/view/63045.html
		// ((x - cx)^2 / (rWidth^2) + (y - cy)^2) / (rHeight^2) < 1
		
		float xx 			= x - cx;
		float yy 			= y - cy;
		float distance 		= (xx*xx)/(rw*rw) + (yy*yy)/(rh*rh);
		return distance < 1.0f;
	}
	
	/**
	 * Test is a point is inside a box
	 * @param x	The x-location to test
	 * @param y	The y-location to test
	 * @param bx	The box's left x-location
	 * @param by	The box's top y-location
	 * @param bw	The box's width
	 * @param bh	The box's height
	 * @return	true is the point is inside the box
	 */
	public static final boolean testPointInsideBox(float x, float y, float bx, float by, float bw, float bh) {
		
		float endX = bx + bw;
		float endY = by + bh;
		
		return 	(x >= x) 	 	&&
				(x <= endX) 	&&
				(y >= y) 	 	&&
				(y <= endY);
	}
	
	/**
	 * Get the cross product of two 2D points
	 * @param x1	point 1 x-value
	 * @param y1	point 1 y-value
	 * @param x2	point 2 x-value
	 * @param y2	point 2 y-value
	 * @return	The cross product
	 */	
	public static final float crossProduct(float x1, float y1, float x2, float y2) {
		// CrossProduct = ((x1 * y2) - (x2 * y1))
		
		//return (x1 * y2) - (x2 * y1);
		return y2*x1 - x2*y1;
	}
	
	/**
	 * Get the dot product of two 2D points
	 * @param x1	point 1 x-value
	 * @param y1	point 1 y-value
	 * @param x2	point 2 x-value
	 * @param y2	point 2 y-value
	 * @return	The dot product
	 */
	public static final float dotProduct(float x1, float y1, float x2, float y2) {
		// DotProduct = ((x1 * x2) + (y1 * y2))
		
		return (x1 * x2) + (y1 * y2);
	}
	
	/**
	 * Convert an index value into a 2D point
	 * @param index	The integer index
	 * @param width	The width of the 2D array (column count)
	 * @return	A 2D point where top left is the first index.
	 * 			As the index increases, the array moves from
	 * 			left to right, and moves downwards.
	 */
	public static final Vec2F indexToPoint(int index, int width) {
		
		if (width <= 0) {
			return new Vec2F();
		}
		
		int y = index / width;
		int x = index % width;
		
		return new Vec2F(x, y);
	}
	
	/**
	 * Convert a 2D point into an index
	 * @param x	The x-value
	 * @param y	The y-value
	 * @param width	The width of the 2D array (column count)
	 * @return	The integer index value
	 */
	public static final int pointToIndex(int x, int y, int width) {		
		return y * width + x;
	}
}