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
 * Copyright 2012 (C)
 * 
 * Created on	: 21, August, 2012
 * Author		: David McCoy
 * Description	: A Vector class
 * ----------------------------------------------------------------------------
 * Revision History
 * ----------------------------------------------------------------------------
 * Edited on 	: 19, October, 2012
 * Author		: David McCoy
 * Description	: Simplified the class, 
 * 				: Lots of code refactoring.
 * ----------------------------------------------------------------------------
 * Edited on 	: 19, April, 2014
 * Author		: David McCoy
 * Description	: Added proper Javadoc documentation.
 * 				: Added some features.
 * ----------------------------------------------------------------------------
 * Edited on 	: 16, December, 2014
 * Author		: David McCoy
 * Description	: Improved documentation.
 * 				: Fixed floor, ceil and round.
 * ----------------------------------------------------------------------------
 * Edited on 	: 20, January, 2016
 * Author		: David McCoy
 * Description	: Refactored everything as I ported over to C++.
 * ----------------------------------------------------------------------------
 * Edited on 	: 21, January, 2016
 * Author		: David McCoy
 * Description	: A few minor changes and additions
 */

package com.bulletdave.math.vector;

import com.bulletdave.math.Compute;


public class Vec2F {
	
	public float x;		// This Vec2F's x location
	public float y;		// This Vec2F's y location
	
	/**
	 * Default constructor sets values to 0.
	 */
	public Vec2F() {
		set(0, 0);
	}
	
	/**
	 * Construct a Vec2F with given (x, y).
	 * @param x	A designated x location.
	 * @param y	A designated y location.
	 */
	public Vec2F(float x, float y) {
		set(x, y);
	}
	
	/**
	 * Copy constructor
	 * @param point	The point the be copied
	 */
	public Vec2F(Vec2F point) {
		set(point);
	}
	
	/**
	 * Set this Vec2F's location to a given coordinate (x, y).
	 * @param x	A designated x location.
	 * @param y	A designated y location.
	 * @return	A reference to this class
	 */
	public final Vec2F set(float x, float y) {
		
		this.x = x;
		this.y = y;
		
		return this;
	}
	
	/**
	 * Set this Vec2F's location to that of a given point.
	 * @param point	A given point to set the location to.
	 * @return	A reference to this class
	 */
	public final Vec2F set(Vec2F point) {
		
		this.x = point.x;
		this.y = point.y;
		
		return this;
	}
	
	/**
	 * Set the x location value
	 * @param	val The value to set the location by
	 * @return	A reference to this class
	 */
	public final Vec2F setX(float val) {
		this.x = val;
		return this;
	}
	
	/**
	 * Set the y location value
	 * @param	val The value to set the location by
	 * @return	A reference to this class
	 */
	public final Vec2F setY(float val) {
		this.y = val;
		return this;
	}

	/**
	 * Set this Vec2F's location to a point corresponding to
	 * an index read from top left to bottom right order in a
	 * 2D array.
	 * @param index	A index to be converted into a point.
	 * @param width	The size of our 2D array in width.
	 * @return	A reference to this class on success.
				A new instance defaulted to (0, 0) on fail.
	 */
	public final Vec2F setUsingIndex(int index, int width) {
		
		if (index < 0 || width < 0) {
			return new Vec2F();
		}

		this.x = (float)(index / width);
		this.y = (float)(index % width);
		
		return this;
	}
	
	/**
	 * Returns the x location of this point.
	 * @return	The x location of this point.
	 */
	public final float getX() {
		return x;
	}
	
	/**
	 * Returns the y location of this point.
	 * @return	The y location of this point.
	 */
	public final float getY() {
		return y;
	}
	
	/**
	 * Calculates this Vec2F's location relative to the origin (0, 0)
	 * into a squared magnitude.
	 * @return	The magnitude squared
	 */
	public final float getMagnitudeSquared() {
		return (x*x + y*y);
	}
	
	/**
	 * Calculates this Vec2F's location relative to a given point
	 * @param px	Point x's location
	 * @param py	Point y's location
	 * @return	The magnitude between both points
	 */
	public final float getMagnitudeSquared(float px, float py) {
		
		Vec2F diff = clone().negate(px, py);
		return (diff.x*diff.x + diff.y*diff.y);
	}
	
	/**
	 * Calculates this Vec2F's location relative to a given point
	 * into a squared magnitude.
	 * @param point	A given point to get relative magnitude squared from.
	 * @return	The magnitude squared
	 */
	public final float getMagnitudeSquared(Vec2F point) {
		
		Vec2F diff = clone().negate(point);
		return (diff.x*diff.x + diff.y*diff.y);
	}
	
	/**
	 * Calculates this Vec2F's distance from the origin (0, 0).
	 * @return	The distance between this Vec2F, and the origin (0, 0).
	 */
	public final float getMagnitude() {
		return (float) Math.sqrt(getMagnitudeSquared());
	}

	/**
	 * Calculates this Vec2F's distance relative to a given point.
	 * @param px	Target point's x location
	 * @param py	Target point's y location
	 * @return	The distance between both points
	 */
	public final float getMagnitude(float px, float py) {	
		return (float) Math.sqrt(getMagnitudeSquared(px, py));
	}
	
	/**
	 * Calculates this Vec2F's distance relative to a given point.
	 * @param point	A given point to obtain the relative distance from.
	 * @return	The distance between both points.
	 */
	public final float getMagnitude(Vec2F point) {	
		return (float) Math.sqrt(getMagnitudeSquared(point));
	}
	
	/**
	 * Calculates the slope (m), of this given point, 
	 * relative to the origin (0, 0).
	 * @return	The slope (y/x).
	 */
	public final float getSlope() {
		return (y/x);
	}
	
	/**
	 * Calculates the slope (m), relative to a given point.
	 * m = (y2-y1)/(x2-x1)
	 * @param point	A given point to get the slope from.
	 * @return	The slope between two points.
	 */
	public final float getSlope(Vec2F point) {	
		return clone().negate(point).getSlope();
	}
	
	/**
	 * Negative on one side of the line, positive on the other.
	 * @param end	The end of the line from this point
	 * @param test	The test point with respect to the line
	 * @return	A positive or negative float depending on side of line
	 */
	public final float getSidePointOn(Vec2F end, Vec2F test) {
		Vec2F testDiff = test.clone().negate(end);
		Vec2F thisDiff = clone().negate(end);
		return thisDiff.getCrossProduct(testDiff);
	}
	
	/**
	 * Calculates this Vec2F's location relative to the origin (0, 0)
	 * into an angle given in radians.
	 * @return	The angle in radians relative to the origin.
	 */
	public final float getAtan2Theta() {										// ***proper way
		return (float) Math.atan2(y, x);
	}
	
	/**
	 * Calculates the direction of this Vec2F,
	 * relative to the origin (0, 0) in radians.
	 * Corrects the Atan radian offsets to behave as expected.
	 * @return	The direction of this Vec2F in radians from the origin (0, 0).
	 */
	public final float getRadian() {
		
		float rad = getAtan2Theta();
		
		if (rad < 0) {
			rad += Compute.TWO_PI;
		}
		
		return rad;
	}
	
	/**
	 * Calculates the direction in radians of this Vec2F,
	 * relative to a given point.
	 * @param point	A given point to get the direction to.
	 * @return	The direction from this point to a given point in radians.
	 */
	public final float getRadian(Vec2F point) {
		return clone().negate(point).getRadian();
	}
	
	/**
	 * Calculates this Vec2F's dot product relative to a given point.
	 * @param point	A given point to obtain the dot product from.
	 * @return	The dot product between two points.
	 */
	public final float getDotProduct(Vec2F point) {
		return (point.x*x + point.y*y);
	}
	
	/**
	 * Calculates this Vec2F's cross product relative to a given point.
	 * @param point	A given point to obtain the cross product from.
	 * @return	The cross product between two points.
	 */
	public final float getCrossProduct(Vec2F point) {
		return (point.y*x - point.x*y);
	}
	
	/**
	 * Calculates the y intercept (when x is 0), aka b, 
	 * between the line formed by this Vec2F, and a given point.
	 * y = mx + b
	 * @param point	A given point to form a line with.
	 * @return	The y intercept, aka b.
	 */
	public final float getYIntercept(Vec2F point) {
		// return the y intercept from a line
		// b = (y1 - (m * x1))
		
		return (y - (getSlope(point) * x));
	}

	/**
	 * Convert this point's location into an integer index.
	 * Note: Width and location must be positive values.
	 * @param width	The width of our 2D array
	 * @return	The integer index of our point on success.
				0 on failure.
	 */
	public final int getIndex(int width) {

		if (width < 0 || x < 0 || y < 0) {
			return 0;
		}

		int ix = (int)x;
		int iy = (int)y;
		return iy * width + ix;
	}
	
	/**
	 * Solve for x, at y from the line created by this Vec2F and a given point.
	 * @param point	A given point to form a line.
	 * @param y	The y value to solve the function by.
	 * @return	The x solution from the line.
	 * 			NaN if the line is horizontal.
	 */
	public final float solveX(Vec2F point, float y) {
		// x = (y - b) / m
		
		Vec2F diff = clone().negate(point);
		float m = getSlope(point);
		
		if (diff.x != 0 && diff.y != 0) {
			// if slope is normal, do usual operations
			float b = getYIntercept(point);
			return ((y - b) / m);
		} else if (diff.x == 0) {
			// if x = 0, horizontal line, therefore return any x is a solution
			return x;
		}
		
		// Cannot solve for y if line is horizontal.
		return Float.NaN;
	}
	
	/**
	 * Solve for x, at y from this Vec2F given a slope.
	 * @param m	A given slope.
	 * @param y	The target y location to solve x at.
	 * @return The solution for x.
	 */
	public final float solveX(float m, float y) {
		// y - y1 = m(x - x1)
		// x = (y - y1 + m*x1) / m
		return (y - this.y + m * this.x) / m;
	}
	
	/**
	 * Solve for y, at x from the line created by this Vec2F and a given point.
	 * @param point	A given point to form a line.
	 * @param x	The x value to solve the function by.
	 * @return	The y solution from the line.
	 * 			NaN if the line is vertical.
	 */
	public final float solveY(Vec2F point, float x) {
		// solve the value of y in a line given x
		// y = (m * x) + b
		
		Vec2F diff = clone().negate(point);
		float m = getSlope(point);

		if (diff.y != 0 && diff.x != 0) {
			// if slope is normal, do usual operations
			float b = getYIntercept(point);
			return (m * x) + b;
		} else if (diff.y == 0) {
			// if y = 0, vertical line, therefore return any y is a solution
			return y;
		}
		
		// Cannot solve for x if line is vertical.
		return Float.NaN;
	}
	
	/**
	 * Solve for y, at x from this Vec2F given a slope.
	 * @param m	A given slope.
	 * @param x	The target x location to solve y at.
	 * @return The solution for y.
	 */
	public final float solveY(float m, float x) {
		// y - y1 = m(x - x1)
		// y = m*x - m*x1 + y1
		return (m * x) - (m * this.x) + this.y;
	}
	
	/**
	 * Test collision with this point onto a defined triangle
	 * @param a	Point 1 of the triangle
	 * @param b	Point 2 of the triangle
	 * @param c	Point 3 of the triangle
	 * @return	True on collision, False of no collision
	 */
	public final boolean testTriangle(Vec2F a, Vec2F b, Vec2F c) {

		boolean b1 = a.getSidePointOn(this, b) > 0.0f;
		boolean b2 = b.getSidePointOn(this, c) > 0.0f;
		boolean b3 = c.getSidePointOn(this, a) > 0.0f;
		
		return (b1 == b2 && b2 == b3);
	}

	/**
	 * Test collision with this point onto a defined circle
	 * @param cx	Center x location of circle
	 * @param cy	Center y location of circle
	 * @param cr	Circle's radius
	 * @return	True on collision, False of no collision
	 */
	public final boolean testCircle(float cx, float cy, float cr) {
		// ((x - cx)^2 + (y - cy)^2) < (radius^2)

		float distance = getMagnitudeSquared(cx, cy);
		float radiusSquared = cr*cr;
		return distance <= radiusSquared;
	}

	/**
	 * Test collision with this point onto a defined circle
	 * @param center	Center location of circle to test
	 * @param radius	The circles radius
	 * @return	True on collision, False of no collision
	 */
	public final boolean testCircle(Vec2F center, float radius) {
		// ((x - cx)^2 + (y - cy)^2) < (radius^2)

		float distance = getMagnitudeSquared(center);
		float radiusSquared = radius*radius;
		return distance <= radiusSquared;
	}

	/**
	 * Test collision with this point onto a defined ellipse
	 * @param cx	Center x location of ellipse
	 * @param cy	Center x location of ellipse
	 * @param rw	Ellipse's width radius
	 * @param rh	Ellipse's height radius
	 * @return	True on collision, False of no collision
	 */
	public final boolean testEllipse(float cx, float cy, float rw, float rh) {
		// http://mathforum.org/library/drmath/view/63045.html
		// ((x - cx)^2 / (rWidth^2) + (y - cy)^2) / (rHeight^2) < 1

		float xx 			= x - cx;
		float yy 			= y - cy;
		float distance 		= (xx*xx)/(rw*rw) + (yy*yy)/(rh*rh);
		return distance <= 1.0f;
	}

	/**
	 * Test collision with this point onto a defined ellipse
	 * @param center	Center location of ellipse to test
	 * @param rw	Ellipse's width radius
	 * @param rh	Ellipse's height radius
	 * @return	True on collision, False of no collision
	 */
	public final boolean testEllipse(Vec2F center, float rw, float rh) {
		// http://mathforum.org/library/drmath/view/63045.html
		// ((x - cx)^2 / (rWidth^2) + (y - cy)^2) / (rHeight^2) < 1

		float xx 			= x - center.x;
		float yy 			= y - center.y;
		float distance 		= (xx*xx)/(rw*rw) + (yy*yy)/(rh*rh);
		return distance <= 1.0f;
	}

	/**
	 * Test collision with this point onto a defined rectangle region.
	 * Note: provided location is not the center of the rectangle.
	 * @param sx	Minimum y value
	 * @param sy	Minimum x value
	 * @param w	Rectangle's width
	 * @param h	Rectangle's height
	 * @return	True on collision, False of no collision
	 */
	public final boolean testBoxAABB(float sx, float sy, float w, float h) {
		
		float endX = sx + w;
		float endY = sy + h;
		
		return 	(x >= sx) 	 	&&
				(x <= endX) 	&&
				(y >= sy) 	 	&&
				(y <= endY);
	}

	/**
	 * Test collision with this point onto a defined rectangle region.
	 * Note: provided location is not the center of the rectangle.
	 * @param location	Minimum location of the rectangle to test
	 * @param w	Rectangle's width
	 * @param h	Rectangle's height
	 * @return	True on collision, False of no collision
	 */
	public final boolean testBoxAABB(Vec2F location, float w, float h) {
		
		float endX = location.x + w;
		float endY = location.y + h;
		
		return 	(x >= location.x) 	 	&&
				(x <= endX) 			&&
				(y >= location.y) 	 	&&
				(y <= endY);
	}
	
	/**
	 * Increases the x location
	 * @param val	The value to increment x by
	 * @return	A reference of this point
	 */
	public final Vec2F incX(float val) {
		x += val;
		return this;
	}
	
	/**
	 * Increases the y location
	 * @param val	The value to increment y by
	 * @return	A reference of this point
	 */
	public final Vec2F incY(float val) {
		y += val;
		return this;
	}
	
	/**
	 * Adds a given amount (x, y) to this Vec2F's location.
	 * @param x	A given x amount to add to this Vec2F's x.
	 * @param y	A given y amount to add to this Vec2F's y.
	 * @return	A reference to this class.
	 */
	public final Vec2F add(float x, float y) {
		
		this.x += x;
		this.y += y;
		
		return this;
	}
	
	/**
	 * Adds a point's offset to this Vec2F's location.
	 * @param point	A given point to add it's offset from.
	 * @return	A reference to this class.
	 */
	public final Vec2F add(Vec2F point) {
		
		this.x += point.x;
		this.y += point.y;
		
		return this;
	}
	
	/**
	 * Negates a given amount (x, y) to this Vec2F's location.
	 * @param x	A given x amount to negate from this Vec2F's x.
	 * @param y	A given y amount to negate from this Vec2F's y.
	 * @return	A reference to this class.
	 */
	public final Vec2F negate(float x, float y) {
		
		this.x -= x;
		this.y -= y;
		
		return this;
	}
	
	/**
	 * Negates a point's offset to this Vec2F's location.
	 * @param point	A given point to negate it's offset from.
	 * @return	A reference to this class.
	 */
	public final Vec2F negate(Vec2F point) {
		
		this.x -= point.x;
		this.y -= point.y;
		
		return this;
	}
	
	/**
	 * Multiplies this Vec2F's location by a given a given scaler.
	 * @param val	A given scaler to scale this Vec2F's location by.
	 * @return	A reference to this class.
	 */
	public final Vec2F multiply(float val) {
		
		this.x *= val;
		this.y *= val;
	
		return this;
	}
	
	/**
	 * Multiplies this Vec2F's location by a given amount (x, y).
	 * @param x	A given x amount to multiply this Vec2F's x by.
	 * @param y	A given y amount to multiply this Vec2F's y by.
	 * @return	A reference to this class.
	 */
	public final Vec2F multiply(float x, float y) {
		
		this.x *= x;
		this.y *= y;
		
		return this;
	}
	
	/**
	 * Multiplies a point's offset to this Vec2F's location.
	 * @param point	A given point to multiply it's offset from.
	 * @return	A reference to this class.
	 */
	public final Vec2F multiply(Vec2F point) {
		
		this.x *= point.x;
		this.y *= point.y;
		
		return this;
	}
	
	/**
	 * Divides this Vec2F's location by a given a given scaler.
	 * @param val	A given scaler to scale this Vec2F's location by.
	 * @return	A reference to this class.
	 */
	public final Vec2F divide(float val) {
		
		this.x /= val;
		this.y /= val;
		
		return this;
	}
	
	/**
	 * Divides this Vec2F's location by a given amount (x, y).
	 * @param x	A given x amount to divide this Vec2F's x by.
	 * @param y	A given y amount to divide this Vec2F's y by.
	 * @return	A reference to this class.
	 */
	public final Vec2F divide(float x, float y) {
		
		this.x /= x;
		this.y /= y;
		
		return this;
	}
	
	/**
	 * Divides this Vec2F's location by a given point's offset.
	 * @param point	A given point to divide this Vec2F's location by.
	 * @return	A reference to this class.
	 */
	public final Vec2F divide(Vec2F point) {
		
		this.x /= point.x;
		this.y /= point.y;
		
		return this;
	}
	
	/**
	 * Rounds up this Vec2F's members.
	 * @return	A reference to this class.
	 */
	public final Vec2F ceil() {
		
		x = (int) Math.ceil(x);
		y = (int) Math.ceil(y);
		
		return this;
	}
	
	/**
	 * Rounds down this Vec2F's members.
	 * @return	A reference to this class.
	 */
	public final Vec2F floor() {
		
		x = (int) Math.floor(x);
		y = (int) Math.floor(y);
		
		return this;
	}
	
	/**
	 * Convert this Vec2F's members into absolute values.
	 * @return	A reference to this class.
	 */
	public final Vec2F abs() {
		
		if (x < 0) x = -x;
		if (y < 0) y = -y;
		
		return this;
	}
	
	/**
	 * Inverts the signs of this Vec2F's members.
	 * @return	A reference to this class.
	 */
	public final Vec2F invert() {
		
		x = -x;
		y = -y;
		
		return this;
	}
	
	/**
	 * Normalized this Vec2F's location.
	 * The coordinates cannot be 0.
	 * @return	A reference to this class.
	 */
	public final Vec2F normalize() {
		
		float mag = getMagnitude();
		if (mag == 0) return this;
		
		if (x != 0) x /= mag;
		if (y != 0)	y /= mag;
		
		return this;
	}
	
	/**
	 * Normalized the difference between two Vec2F.
	 * The coordinates cannot be 0.
	 * @param point	The target vector to get the difference of.
	 * @return	A reference to this class.
	 */
	public final Vec2F normalize(Vec2F point) {
		
		float mag = getMagnitude(point);
		if (mag == 0) return this;
		
		if (x != 0) x /= mag;
		if (y != 0)	y /= mag;
		
		return this;
	}
	
	/**
	 * Rotates this Vec2F around a target point, by a given amount in radians.
	 * @param cx	The x center to rotate around.
	 * @param cy	The y center to rotate around.
	 * @param radians	A given amount of radians to rotate around by.
	 * @return	A reference to this class.
	 */
	public final Vec2F rotate(float cx, float cy, float radians) {
		// x = radius * cos(theta);
		// y = radius * sin(theta);
		
		float radius = getMagnitude(cx, cy);
		float px = radius * (float) Math.cos(-radians);
		float py = radius * (float) Math.sin(-radians);
		
		x = px + cx;
		y = py + cy;
		
		return this;
	}
	
	/**
	 * Rotates this Vec2F around a target point, by a given amount in radians.
	 * @param point	A target point to rotate around.
	 * @param radians	A given amount of radians to rotate around by.
	 * @return	A reference to this class.
	 */
	public final Vec2F rotate(Vec2F point, float radians) {
		// x = radius * cos(theta);
		// y = radius * sin(theta);
		
		float radius = getMagnitude(point);
		float cx = radius * (float) Math.cos(-radians);
		float cy = radius * (float) Math.sin(-radians);
		
		return set(point.clone().add(cx, cy));
	}
	
	/**
	 * Performs linear interpolation along a line determined by this Vec2F's
	 * location, and a given slope of m. x is added to the current location,
	 * and y is solved along the given slope.
	 * @param m	The slope of the line to solve for y at x.
	 * @param xOff	An x offset to add to this Vec2F's x location.
	 * @return	A reference to this class.
	 */
	public final Vec2F lerpY(float m, float xOff) {
		// y - y1 = m(x - x1)
		// y = m*x - m*x1 + y1
	
		y = m*(x + xOff) - m*x + y;
		x = x + xOff;
		return this;
	}
	
	/**
	 * Performs linear interpolation along a line determined by this Vec2F's
	 * location, and a given slope of m. y is added to the current location,
	 * and x is solved along the given slope.
	 * @param m	The slope of the line to solve for x at y.
	 * @param yOff	A y offset to add to this Vec2F's y location.
	 * @return	A reference to this class.
	 */
	public final Vec2F lerpX(float m, float yOff) {
		// y - y1 = m(x - x1)
		// x = (y - y1 + m*x1) / m
	
		x = ((y + yOff) - y + m*x) / m;
		y = y + yOff;
		
		return this;
	}
	
	/**
	 * Performs linear interpolation between this Vec2F, and a given point
	 * based on a ration from the desired distance to move this Vec2F towards
	 * the target point.
	 * @param point	The target point to lerp towards.
	 * @param ratio	The ratio to move this Vec2F by, towards the target point.
	 * @return	A reference to this class.
	 */
	public final Vec2F lerpRatio(Vec2F point, float ratio) {
		// ratio [0-1]
		
		float dist = getMagnitude(point) * ratio;
		Vec2F division = point.clone().negate(this).normalize().multiply(dist);
		
		if (division.x == 0 && division.y == 0) {
			return this;
		} else if (division.x == 0) {
			return add(0, division.y);
		} else if (division.y == 0) {
			return add(division.x, 0);
		} else {
			return add(division);
		}
	}
	
	/**
	 * Performs linear interpolation between this Vec2F, and a given point
	 * based on distance to move to towards the target point.
	 * @param point	A target point to lerp towards.
	 * @param dist	A given distance to lerp by.
	 * @return	A reference to this class.
	 */
	public final Vec2F lerpDistance(Vec2F point, float dist) {
		
		Vec2F division = point.clone().negate(this).normalize().multiply(dist);
		
		if (division.x == 0 && division.y == 0) {
			return this;
		} else if (division.x == 0) {
			return add(0, division.y);
		} else if (division.y == 0) {
			return add(division.x, 0);
		} else {
			return add(division);
		}
	}
	
	public final void cartesianToPolar() {
		// (x, y) -> (radius, radians)
		set(getMagnitude(), getAtan2Theta());
	}
	
	public final void polarToCartesian() {
		// (radius, radians) -> (x, y)
		float r = x;
		float t = y;
		set(r * (float)Math.cos(t), r * (float)Math.sin(t));
	}
	
	@Override
	public Vec2F clone() {
		return new Vec2F(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}
	
	@Override
	public String toString() {
		return "{" + x + ", " + y + "}";
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Vec2F)) return false;
		
		Vec2F point = (Vec2F)obj;
		return ((x + Compute.ERROR >= point.x && x - Compute.ERROR <= point.x) &&
				(y + Compute.ERROR >= point.y && y - Compute.ERROR <= point.y));
	}
	
	/**
	 * Test for equality. Factors in loss of floating point 
	 * precision after arithmetic operations.
	 * @param point	A point to test for equality with this Vec2F.
	 * @return	A reference to this class.
	 */
	public final boolean equals(Vec2F point) {

		return ((x + Compute.ERROR >= point.x && x - Compute.ERROR <= point.x) &&
				(y + Compute.ERROR >= point.y && y - Compute.ERROR <= point.y));
	}

	/**
	 * Test for equality given a threshold. An Compute.ERROR offset is added to
	 * the target threshold to factor in the loss of floating point
	 * precision after arithmetic operations.
	 * @param point	A point to test for equality with this Vec2F.
	 * @param threshold	A given threshold to test for equality.
	 * @return	A reference to this class.
	 */
	public final boolean equals(Vec2F point, float threshold) {
		
		float range = threshold + Compute.ERROR;
		return ((x + range >= point.x && x - range <= point.x) &&
				(y + range >= point.y && y - range <= point.y));
	}
	
	/**
	 * Prints the value of this Vec2F's members.
	 * Vec2F(x, y)
	 */
	public void print() {
		System.out.print(toString());
	}
	
	/**
	 * Prints the value of this Vec2F's members, and adds a new line.
	 * Vec2F(x, y)
	 */
	public void println() {
		System.out.println(toString());
	}
}
