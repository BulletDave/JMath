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
 * Created on	: 16, December, 2014
 * Author		: David McCoy
 * Description	: A Vector class
 */

package com.bulletdave.math.vector;

import com.bulletdave.math.Compute;


public class Vec4F {
	
	public float x;		// this Vec4F's x location
	public float y;		// this Vec4F's y location
	public float z;		// this Vec4F's z location
	public float w;		// this Vec4F's w location
	
	/**
	 * Default constructor sets values to 0.
	 */
	public Vec4F() {
		set(0, 0, 0, 0);
	}
	
	/**
	 * Construct a Vec2F with given (x, y, z, w).
	 * @param x	A designated x location.
	 * @param y	A designated y location.
	 * @param z	A designated z location.
	 * @param w	A designated w location.
	 */
	public Vec4F(float x, float y, float z, float w) {
		set(x, y, z, w);
	}
	
	/**
	 * Copy constructor
	 * @param point	The point the be copied
	 */
	public Vec4F(Vec4F point) {
		set(point);
	}
	
	/**
	 * Set this Vec4F's location to a given coordinate (x, y, z, w).
	 * @param x	A designated x location.
	 * @param y	A designated y location.
	 * @param z	A designated z location.
	 * @param w	A designated w location.
	 * @return	A reference to this class
	 */
	public final Vec4F set(float x, float y, float z, float w) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		
		return this;
	}
	
	/**
	 * Set this Vec4F's location to that of a given point.
	 * @param point	A given point to set the location to.
	 * @return	A reference to this class
	 */
	public final Vec4F set(Vec4F point) {
		
		this.x = point.x;
		this.y = point.y;
		this.z = point.z;
		this.w = point.w;
		
		return this;
	}
	
	/**
	 * Set the x location value
	 * @param	val The value to set the location by
	 * @return	A reference to this class
	 */
	public final Vec4F setX(float val) {
		this.x = val;
		return this;
	}
	
	/**
	 * Set the y location value
	 * @param	val The value to set the location by
	 * @return	A reference to this class
	 */
	public final Vec4F setY(float val) {
		this.y = val;
		return this;
	}
	
	/**
	 * Set the z location value
	 * @param	val The value to set the location by
	 * @return	A reference to this class
	 */
	public final Vec4F setZ(float val) {
		this.z = val;
		return this;
	}
	
	/**
	 * Set the w location value
	 * @param	val The value to set the location by
	 * @return	A reference to this class
	 */
	public final Vec4F setW(float val) {
		this.w = val;
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
	 * Returns the z location of this point.
	 * @return	The z location of this point.
	 */
	public final float getZ() {
		return z;
	}
	
	/**
	 * Returns the w location of this point.
	 * @return	The w location of this point.
	 */
	public final float getW() {
		return w;
	}
	
	/**
	 * Calculates this Vec4F's location relative to the origin (0, 0, 0, 0)
	 * into a squared magnitude.
	 * @return	The magnitude squared
	 */
	public final float getMagnitudeSquared() {
		return (x*x + y*y + z*z + w*w);
	}
	
	/**
	 * Calculates this Vec3F's location relative to a given point
	 * @param px	Point x's location
	 * @param py	Point y's location
	 * @param pz	Point z's location
	 * @param pw	Point w's location
	 * @return	The magnitude between both points
	 */
	public final float getMagnitudeSquared(float px, float py, float pz, float pw) {
		
		Vec4F diff = clone().negate(px, py, pz, pw);
		return (diff.x*diff.x + diff.y*diff.y + diff.z*diff.z + diff.w*diff.w);
	}
	
	/**
	 * Calculates this Vec4F's location relative to a given point
	 * into a squared magnitude.
	 * @param point	A given point to get relative magnitude squared from.
	 * @return	The magnitude squared
	 */
	public final float getMagnitudeSquared(Vec4F point) {
		
		Vec4F diff = clone().negate(point);
		return diff.x*diff.x + diff.y*diff.y + 
			   diff.z*diff.z + diff.w*diff.w;
	}
	
	/**
	 * Calculates this Vec4F's distance from the origin (0, 0, 0, 0).
	 * @return	The distance between this Vec4F, and the origin (0, 0, 0, 0).
	 */
	public final float getMagnitude() {
		return (float) Math.sqrt(getMagnitudeSquared());
	}
	
	/**
	 * Calculates this Vec4F's distance relative to a given point.
	 * @param px	Target point's x location
	 * @param py	Target point's y location
	 * @param pz	Target point's z location
	 * @param pw	Target point's w location
	 * @return	The distance between both points
	 */
	public final float getMagnitude(float px, float py, float pz, float pw) {	
		return (float) Math.sqrt(getMagnitudeSquared(px, py, pz, pw));
	}
	
	/**
	 * Calculates this Vec4F's distance relative to a given point.
	 * @param point	A given point to obtain the relative distance from.
	 * @return	The distance between both points.
	 */
	public final float getMagnitude(Vec4F point) {	
		return (float) Math.sqrt(getMagnitudeSquared(point));
	}
	
	/**
	 * Calculates this Vec4F's dot product relative to a given point.
	 * @param point	A given point to obtain the dot product from.
	 * @return	The dot product between two points.
	 */
	public final float getDotProduct(Vec4F point) {
		return (point.x*x + point.y*y + point.z*z + point.w*w);
	}
	
	/**
	 * Increases the x location
	 * @param val	The value to increase x by
	 * @return	A reference of this point
	 */
	public final Vec4F incX(float val) {
		x += val;
		return this;
	}
	
	/**
	 * Increases the y location
	 * @param val	The value to increase y by
	 * @return	A reference of this point
	 */
	public final Vec4F incY(float val) {
		y += val;
		return this;
	}
	
	/**
	 * Increases the z location
	 * @param val	The value to increase z by
	 * @return	A reference of this point
	 */
	public final Vec4F incZ(float val) {
		z += val;
		return this;
	}
	
	/**
	 * Increases the w location
	 * @param val	The value to increase w by
	 * @return	A reference of this point
	 */
	public final Vec4F incW(float val) {
		w += val;
		return this;
	}
	
	/**
	 * Adds a given amount (x, y) to this Vec4F's location.
	 * @param x	A given x amount to add to this Vec4F's x.
	 * @param y	A given y amount to add to this Vec4F's x.
	 * @param z	A given z amount to add to this Vec4F's z.
	 * @param w	A given w amount to add to this Vec4F's w.
	 * @return	A reference to this class.
	 */
	public final Vec4F add(float x, float y, float z, float w) {
		
		this.x += x;
		this.y += y;
		this.z += z;
		this.w += w;
		
		return this;
	}
	
	/**
	 * Adds a point's offset to this Vec4F's location.
	 * @param point	A given point to add it's offset from.
	 * @return	A reference to this class.
	 */
	public final Vec4F add(Vec4F point) {
		
		this.x += point.x;
		this.y += point.y;
		this.z += point.z;
		this.w += point.w;
		
		return this;
	}
	
	/**
	 * Negates a given amount (x, y) to this Vec4F's location.
	 * @param x	A given x amount to negate from this Vec4F's x.
	 * @param y	A given y amount to negate from this Vec4F's y.
	 * @param z	A given z amount to negate from this Vec4F's z.
	 * @param w	A given w amount to negate from this Vec4F's w.
	 * @return	A reference to this class.
	 */
	public final Vec4F negate(float x, float y, float z, float w) {
		
		this.x -= x;
		this.y -= y;
		this.z -= z;
		this.w -= w;
		
		return this;
	}
	
	/**
	 * Negates a point's offset to this Vec4F's location.
	 * @param point	A given point to negate it's offset from.
	 * @return	A reference to this class.
	 */
	public final Vec4F negate(Vec4F point) {
		
		this.x -= point.x;
		this.y -= point.y;
		this.z -= point.z;
		this.w -= point.w;
		
		return this;
	}
	
	/**
	 * Multiplies this Vec4F's location by a given amount (x, y, z, w).
	 * @param x	A given x amount to multiply this Vec4F's x by.
	 * @param y	A given y amount to multiply this Vec4F's y by.
	 * @param z	A given z amount to multiply this Vec4F's z by.
	 * @param w	A given w amount to multiply this Vec4F's w by.
	 * @return	A reference to this class.
	 */
	public final Vec4F multiply(float x, float y, float z, float w) {
		
		this.x *= x;
		this.y *= y;
		this.z *= z;
		this.w *= w;
		
		return this;
	}
	
	/**
	 * Multiplies a point's offset to this Vec4F's location.
	 * @param point	A given point to multiply it's offset from.
	 * @return	A reference to this class.
	 */
	public final Vec4F multiply(Vec4F point) {
		
		this.x *= point.x;
		this.y *= point.y;
		this.z *= point.z;
		this.w *= point.w;
		
		return this;
	}
	
	/**
	 * Multiplies this Vec4F's location by a given a given scaler.
	 * @param val	A given scaler to scale this Vec4F's location by.
	 * @return	A reference to this class.
	 */
	public final Vec4F multiply(float val) {
		
		this.x *= val;
		this.y *= val;
		this.z *= val;
		this.w *= w;
	
		return this;
	}
	
	/**
	 * Divides this Vec4F's location by a given a given scaler.
	 * @param val	A given scaler to scale this Vec4F's location by.
	 * @return	A reference to this class.
	 */
	public final Vec4F divide(float val) {
		
		this.x /= val;
		this.y /= val;
		this.z /= val;
		this.w /= val;
		
		return this;
	}
	
	/**
	 * Divides this Vec4F's location by a given amount (x, y, z, w).
	 * @param x	A given x amount to divide this Vec4F's x by.
	 * @param y	A given y amount to divide this Vec4F's y by.
	 * @param z	A given z amount to divide this Vec4F's z by.
	 * @param w	A given w amount to divide this Vec4F's w by.
	 * @return	A reference to this class.
	 */
	public final Vec4F divide(float x, float y, float z, float w) {
		
		this.x /= x;
		this.y /= y;
		this.z /= z;
		this.w /= w;
		
		return this;
	}
	
	/**
	 * Divides this Vec4F's location by a given point's offset.
	 * @param point	A given point to divide this Vec4F's location by.
	 * @return	A reference to this class.
	 */
	public final Vec4F divide(Vec4F point) {
		
		this.x /= point.x;
		this.y /= point.y;
		this.z /= point.z;
		this.w /= point.w;
		
		return this;
	}
	
	/**
	 * Rounds up this Vec4F's members.
	 * @return	A reference to this class.
	 */
	public final Vec4F ceil() {
		
		x = (int) Math.ceil(x);
		y = (int) Math.ceil(y);
		z = (int) Math.ceil(z);
		w = (int) Math.ceil(w);
		
		return this;
	}

	/**
	 * Rounds down this Vec4F's members.
	 * @return	A reference to this class.
	 */
	public final Vec4F floor() {
		
		x = (int) Math.floor(x);
		y = (int) Math.floor(y);
		z = (int) Math.floor(z);
		w = (int) Math.floor(w);
		
		return this;
	}

	/**
	 * Convert this Vec4F's members into absolute values.
	 * @return	A reference to this class.
	 */
	public final Vec4F abs() {
		
		if (x < 0) x = -x;
		if (y < 0) y = -y;
		if (z < 0) z = -z;
		if (w < 0) w = -w;
		
		return this;
	}

	/**
	 * Inverts the signs of this Vec4F's members.
	 * @return	A reference to this class.
	 */
	public final Vec4F invert() {
		
		x = -x;
		y = -y;
		z = -z;
		w = -w;
		
		return this;
	}

	/**
	 * Normalized this Vec4F's location.
	 * The coordinates cannot be 0.
	 * @return	A reference to this class.
	 */
	public final Vec4F normalize() {
		
		float mag = getMagnitude();
		if (mag == 0) return this;
		
		if (x != 0) x /= mag;
		if (y != 0)	y /= mag;
		if (z != 0) z /= mag;
		if (w != 0) w /= mag;
		
		return this;
	}
	
	/**
	 * Normalized the difference between two Vec4F.
	 * The coordinates cannot be 0.
	 * @param point	The target vector to get the difference of.
	 * @return	A reference to this class.
	 */
	public final Vec4F normalize(Vec4F point) {
		
		float mag = getMagnitude(point);
		if (mag == 0) return this;
		
		if (x != 0) x /= mag;
		if (y != 0)	y /= mag;
		if (z != 0)	z /= mag;
		if (w != 0)	w /= mag;
		
		return this;
	}
	
	@Override
	public Vec4F clone() {
		return new Vec4F(this);
	}
	
	@Override
	public String toString() {
		return "{" + x + ", " + y + ", " + z + ", " + w + "}";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Vec4F)) return false;
		
		Vec4F point = (Vec4F) obj;
		return ((x + Compute.ERROR >= point.x && x - Compute.ERROR <= point.x) &&
				(y + Compute.ERROR >= point.y && y - Compute.ERROR <= point.y) &&
				(z + Compute.ERROR >= point.z && z - Compute.ERROR <= point.z) &&
				(w + Compute.ERROR >= point.w && w - Compute.ERROR <= point.w));
	}
	
	/**
	 * Test for equality. Factors in loss of floating point 
	 * precision after arithmetic operations.
	 * @param point	A point to test for equality with this Vec4F.
	 * @return	A reference to this class.
	 */
	public final boolean equals(Vec4F point) {

		return ((x + Compute.ERROR >= point.x && x - Compute.ERROR <= point.x) &&
				(y + Compute.ERROR >= point.y && y - Compute.ERROR <= point.y) &&
				(z + Compute.ERROR >= point.z && z - Compute.ERROR <= point.z) &&
				(w + Compute.ERROR >= point.w && w - Compute.ERROR <= point.w));
	}

	/**
	 * Test for equality given a threshold. An Compute.ERROR offset is added to
	 * the target threshold to factor in the loss of floating point
	 * precision after arithmetic operations.
	 * @param point	A point to test for equality with this Vec4F.
	 * @param threshold	A given threshold to test for equality.
	 * @return	A reference to this class.
	 */
	public final boolean equals(Vec4F point, float threshold) {
		
		float range = threshold + Compute.ERROR;
		return ((x + range >= point.x && x - range <= point.x) &&
				(y + range >= point.y && y - range <= point.y) &&
				(z + range >= point.z && z - range <= point.z) &&
				(w + range >= point.w && w - range <= point.w));
	}
	
	/**
	 * Prints the value of this Vec4F's members.
	 * Vec4F(x, y, z, w)
	 */
	public void print() {
		System.out.print(toString());
	}
	
	/**
	 * Prints the value of this Vec4F's members, and adds a new line.
	 * Vec4F(x, y, z, w)
	 */
	public void println() {
		System.out.println(toString());
	}
}
