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


public class Vec3F {
	
	public float x;		// this Vec3F's x location
	public float y;		// this Vec3F's y location
	public float z;		// this Vec3F's z location
	
	/**
	 * Default constructor sets values to 0.
	 */
	public Vec3F() {
		set(0, 0, 0);
	}
	
	/**
	 * Construct a Vec2F with given (x, y, z).
	 * @param x	A designated x location.
	 * @param y	A designated y location.
	 * @param z	A designated z location.
	 */
	public Vec3F(float x, float y, float z) {
		set(x, y, z);
	}
	
	/**
	 * Copy constructor
	 * @param point	The point the be copied
	 */
	public Vec3F(Vec3F point) {
		set(point);
	}
	
	/**
	 * Set this Vec3F's location to a given coordinate (x, y).
	 * @param x	A designated x location.
	 * @param y	A designated y location.
	 * @param z A designated z location.
	 * @return	A reference to this class
	 */
	public final Vec3F set(float x, float y, float z) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		return this;
	}
	
	/**
	 * Set this Vec3F's location to that of a given point.
	 * @param point	A given point to set the location to.
	 * @return	A reference to this class
	 */
	public final Vec3F set(Vec3F point) {
		
		this.x = point.x;
		this.y = point.y;
		this.z = point.z;
		
		return this;
	}
	
	/**
	 * Set the x location value
	 * @param	val The value to set the location by
	 * @return	A reference to this class
	 */
	public final Vec3F setX(float val) {
		this.x = val;
		return this;
	}
	
	/**
	 * Set the y location value
	 * @param	val The value to set the location by
	 * @return	A reference to this class
	 */
	public final Vec3F setY(float val) {
		this.y = val;
		return this;
	}
	
	/**
	 * Set the z location value
	 * @param	val The value to set the location by
	 * @return	A reference to this class
	 */
	public final Vec3F setZ(float val) {
		this.z = val;
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
	 * Calculates this Vec3F's location relative to the origin (0, 0, 0)
	 * into a squared magnitude.
	 * @return	The magnitude squared
	 */
	public final float getMagnitudeSquared() {
		return (x*x + y*y + z*z);
	}
	
	/**
	 * Calculates this Vec3F's location relative to a given point
	 * @param px	Point x's location
	 * @param py	Point y's location
	 * @param pz	Point z's location
	 * @return	The magnitude between both points
	 */
	public final float getMagnitudeSquared(float px, float py, float pz) {
		
		Vec3F diff = clone().negate(px, py, pz);
		return (diff.x*diff.x + diff.y*diff.y + diff.z*diff.z);
	}
	
	/**
	 * Calculates this Vec3F's location relative to a given point
	 * into a squared magnitude.
	 * @param point	A given point to get relative magnitude squared from.
	 * @return	The magnitude squared
	 */
	public final float getMagnitudeSquared(Vec3F point) {
		
		Vec3F diff = clone().negate(point);
		return (diff.x*diff.x + diff.y*diff.y + diff.z*diff.z);
	}
	
	/**
	 * Calculates this Vec3F's distance from the origin (0, 0, 0).
	 * @return	The distance between this Vec3F, and the origin (0, 0, 0).
	 */
	public final float getMagnitude() {
		return (float) Math.sqrt(getMagnitudeSquared());
	}
	
	/**
	 * Calculates this Vec3F's distance relative to a given point.
	 * @param px	Target point's x location
	 * @param py	Target point's y location
	 * @param pz	Target point's z location
	 * @return	The distance between both points
	 */
	public final float getMagnitude(float px, float py, float pz) {	
		return (float) Math.sqrt(getMagnitudeSquared(px, py, pz));
	}
	
	/**
	 * Calculates this Vec3F's distance relative to a given point.
	 * @param point	A given point to obtain the relative distance from.
	 * @return	The distance between both points.
	 */
	public final float getMagnitude(Vec3F point) {	
		return (float) Math.sqrt(getMagnitudeSquared(point));
	}
	
	/**
	 * Calculates this Vec3F's dot product relative to a given point.
	 * @param point	A given point to obtain the dot product from.
	 * @return	The dot product between two points.
	 */
	public final float getDotProduct(Vec3F point) {
		return (point.x*x + point.y*y + point.z*z);
	}
	
	/**
	 * Calculates this Vec3F's cross product relative to a given point.
	 * @param point	A given point to obtain the cross product from.
	 * @return	The cross product between two points.
	 */
	public final Vec3F getCrossProduct(Vec3F point) {
		return new Vec3F(
				y*point.z - z*point.y,
				z*point.x - x*point.z,
				x*point.y - y*point.x);
	}
	
	/**
	 * Test collision with this point onto a defined spherical region.
	 * @param center	Sphere's center location
	 * @param radius	Sphere's radius
	 * @return	True on collision, False of no collision
	 */
	public final boolean testSphere(Vec3F center, float radius) {
		// ((x - cx)^2 + (y - cy)^2 + (z - cz)^2) < (radius^2)
		
		float distance = getMagnitude(center);
		float radiusSquared = radius*radius;
		return distance < radiusSquared;
	}
	
	/**
	 * Test collision with this point onto a defined rectangular box region.
	 * @param location 	Box's minimum location
	 * @param w	Box's width
	 * @param h	Box's height
	 * @param d	Box's depth
	 * @return	True on collision, False of no collision
	 */
	public final boolean testBoxAABB(Vec3F location, float w, float h, float d) {
		
		float endX = location.x + w;
		float endY = location.y + h;
		float endZ = location.z + d;
		
		return 	(x >= location.x) 	 	&&
				(x <= endX) 			&&
				(y >= location.y) 	 	&&
				(y <= endY)				&&
				(z >= location.z) 	 	&&
				(z <= endZ);
	}
	
	/**
	 * Increases the x location
	 * @param val	The value to increase x by
	 * @return	A reference of this point
	 */
	public final Vec3F incX(float val) {
		x += val;
		return this;
	}
	
	/**
	 * Increases the y location
	 * @param val	The value to increase y by
	 * @return	A reference of this point
	 */
	public final Vec3F incY(float val) {
		y += val;
		return this;
	}
	
	/**
	 * Increases the z location
	 * @param val	The value to increase z by
	 * @return	A reference of this point
	 */
	public final Vec3F incZ(float val) {
		z += val;
		return this;
	}
	
	/**
	 * Adds a given amount (x, y, z) to this Vec3F's location.
	 * @param x	A given x amount to add to this Vec3F's x.
	 * @param y	A given y amount to add to this Vec3F's y.
	 * @param z A given z amount to add to this Vec3F's z.
	 * @return	A reference to this class.
	 */
	public final Vec3F add(float x, float y, float z) {
		
		this.x += x;
		this.y += y;
		this.z += z;
		
		return this;
	}
	
	/**
	 * Adds a point's offset to this Vec3F's location.
	 * @param point	A given point to add it's offset from.
	 * @return	A reference to this class.
	 */
	public final Vec3F add(Vec3F point) {
		
		this.x += point.x;
		this.y += point.y;
		this.z += point.z;
		
		return this;
	}
	
	/**
	 * Negates a given amount (x, y, z) to this Vec3F's location.
	 * @param x	A given x amount to negate from this Vec3F's x.
	 * @param y	A given y amount to negate from this Vec3F's y.
	 * @param z A given z amount to negate from this Vec3F's z.
	 * @return	A reference to this class.
	 */
	public final Vec3F negate(float x, float y, float z) {
		
		this.x -= x;
		this.y -= y;
		this.z -= z;
		
		return this;
	}
	
	/**
	 * Negates a point's offset to this Vec3F's location.
	 * @param point	A given point to negate it's offset from.
	 * @return	A reference to this class.
	 */
	public final Vec3F negate(Vec3F point) {
		
		this.x -= point.x;
		this.y -= point.y;
		this.z -= point.z;
		
		return this;
	}
	
	/**
	 * Multiplies this Vec3F's location by a given a given scaler.
	 * @param val	A given scaler to scale this Vec3F's location by.
	 * @return	A reference to this class.
	 */
	public final Vec3F multiply(float val) {
		
		this.x *= val;
		this.y *= val;
		this.z *= val;
	
		return this;
	}
	
	/**
	 * Multiplies this Vec3F's location by a given amount (x, y, z).
	 * @param x	A given x amount to multiply this Vec3F's x by.
	 * @param y	A given y amount to multiply this Vec3F's y by.
	 * @param z A given a amount to multiply this Vec3F's z by.
	 * @return	A reference to this class.
	 */
	public final Vec3F multiply(float x, float y, float z) {
		
		this.x *= x;
		this.y *= y;
		this.z *= z;
		
		return this;
	}
	
	/**
	 * Multiplies a point's offset to this Vec3F's location.
	 * @param point	A given point to multiply it's offset from.
	 * @return	A reference to this class.
	 */
	public final Vec3F multiply(Vec3F point) {
		
		this.x *= point.x;
		this.y *= point.y;
		this.z *= point.z;
		
		return this;
	}
	
	/**
	 * Divides this Vec3F's location by a given amount (x, y, z).
	 * @param x	A given x amount to divide this Vec3F's x by.
	 * @param y	A given y amount to divide this Vec3F's y by.
	 * @param z A given z amount to divide this Vec3F's z by.
	 * @return	A reference to this class.
	 */
	public final Vec3F divide(float x, float y, float z) {
		
		this.x /= x;
		this.y /= y;
		this.z /= z;
		
		return this;
	}
	
	/**
	 * Divides this Vec3F's location by a given point's offset.
	 * @param point	A given point to divide this Vec3F's location by.
	 * @return	A reference to this class.
	 */
	public final Vec3F divide(Vec3F point) {
		
		this.x /= point.x;
		this.y /= point.y;
		this.z /= point.z;
		
		return this;
	}
	
	/**
	 * Divides this Vec3F's location by a given a given scaler.
	 * @param val	A given scaler to scale this Vec3F's location by.
	 * @return	A reference to this class.
	 */
	public final Vec3F divide(float val) {
		
		this.x /= val;
		this.y /= val;
		this.z /= val;
		
		return this;
	}
	
	/**
	 * Rounds up this Vec3F's members.
	 * @return	A reference to this class.
	 */
	public final Vec3F ceil() {
		
		x = (int) Math.ceil(x);
		y = (int) Math.ceil(y);
		z = (int) Math.ceil(z);
		
		return this;
	}

	/**
	 * Rounds down this Vec3F's members.
	 * @return	A reference to this class.
	 */
	public final Vec3F floor() {
		
		x = (int) Math.floor(x);
		y = (int) Math.floor(y);
		z = (int) Math.floor(z);
		
		return this;
	}

	/**
	 * Convert this Vec3F's members into absolute values.
	 * @return	A reference to this class.
	 */
	public final Vec3F abs() {
		
		if (x < 0) x = -x;
		if (y < 0) y = -y;
		if (z < 0) z = -z;
		
		return this;
	}

	/**
	 * Inverts the signs of this Vec3F's members.
	 * @return	A reference to this class.
	 */
	public final Vec3F invert() {
		
		x = -x;
		y = -y;
		z = -z;
		
		return this;
	}

	/**
	 * Normalized this Vec3F's location.
	 * The coordinates cannot be 0.
	 * @return	A reference to this class.
	 */
	public final Vec3F normalize() {
		
		float mag = getMagnitude();
		if (mag == 0) return this;
		
		if (x != 0) x /= mag;
		if (y != 0)	y /= mag;
		if (z != 0) z /= mag;
		
		return this;
	}
	
	/**
	 * Normalized the difference between two Vec3F.
	 * The coordinates cannot be 0.
	 * @param point	The target vector to get the difference of.
	 * @return	A reference to this class.
	 */
	public final Vec3F normalize(Vec3F point) {
		
		float mag = getMagnitude(point);
		if (mag == 0) return this;
		
		if (x != 0) x /= mag;
		if (y != 0)	y /= mag;
		if (z != 0)	z /= mag;
		
		return this;
	}
	
	@Override
	public Vec3F clone() {
		return new Vec3F(this);
	}
	
	@Override
	public String toString() {
		return "{" + x + ", " + y + ", " + z + "}";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Vec3F)) return false;
		
		Vec3F point = (Vec3F) obj;
		return ((x + Compute.ERROR >= point.x && x - Compute.ERROR <= point.x) &&
				(y + Compute.ERROR >= point.y && y - Compute.ERROR <= point.y) &&
				(z + Compute.ERROR >= point.z && z - Compute.ERROR <= point.z));
	}
	
	/**
	 * Test for equality. Factors in loss of floating point 
	 * precision after arithmetic operations.
	 * @param point	A point to test for equality with this Vec3F.
	 * @return	A reference to this class.
	 */
	public final boolean equals(Vec3F point) {

		return ((x + Compute.ERROR >= point.x && x - Compute.ERROR <= point.x) &&
				(y + Compute.ERROR >= point.y && y - Compute.ERROR <= point.y) &&
				(z + Compute.ERROR >= point.z && z - Compute.ERROR <= point.z));
	}

	/**
	 * Test for equality given a threshold. An Compute.ERROR offset is added to
	 * the target threshold to factor in the loss of floating point
	 * precision after arithmetic operations.
	 * @param point	A point to test for equality with this Vec3F.
	 * @param threshold	A given threshold to test for equality.
	 * @return	A reference to this class.
	 */
	public final boolean equals(Vec3F point, float threshold) {
		
		float range = threshold + Compute.ERROR;
		return ((x + range >= point.x && x - range <= point.x) &&
				(y + range >= point.y && y - range <= point.y) &&
				(z + range >= point.z && z - range <= point.z));
	}
	
	/**
	 * Prints the value of this Vec3F's members.
	 * Vec3F(x, y, z)
	 */
	public void print() {
		System.out.print(toString());
	}
	
	/**
	 * Prints the value of this Vec3F's members, and adds a new line.
	 * Vec3F(x, y, z)
	 */
	public void println() {
		System.out.println(toString());
	}
}
