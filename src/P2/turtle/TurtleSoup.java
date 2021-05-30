/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	
    	turtle.color(PenColor.PINK);
    	for(int i = 0 ; i <3 ; i++) {
    		turtle.forward(sideLength);
    		turtle.turn(90);
    	}
    	turtle.forward(sideLength);
    	
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        return (180-360.0/sides);//外角和等于360读，只需先计算每个外角，再用180-外角即可
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        	double angles = 360/(180-angle);
        	//只需要对angles向上取整即可
        	int a_ngle = (int)angles;//a_ngle为angles的整数部分
        	if((angles-a_ngle)>=0.5)   return (a_ngle+1);//向上取整
        	else return a_ngle;
        	
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double angle = calculateRegularPolygonAngle(sides);
        turtle.color(PenColor.BLUE);
        for(int i = 0 ; i < sides ; i++	) {
        	turtle.forward(sideLength);
        	turtle.turn(180-angle);
        }
    	
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
        	int x = targetX - currentX ;
        	int y = targetY - currentY;
        	double angle = 90.0 - Math.atan2(y, x) / Math.PI * 180 ;
        	if(angle >= currentBearing	)   return (angle - currentBearing ); 
        	else return (360.0-(currentBearing - angle) );
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
       List<Double> angle = new ArrayList<Double>();
       double currentBearing = 0.0 ;
       int currentX = 0, currentY = 0 , targetX = 0 , targetY = 0 ;
       int length = xCoords.size();
       for(int i = 0 ; i < length-1 ; i++) {
    	   currentX = xCoords.get(i);
    	   targetX  = xCoords.get(i+1);
    	   currentY = yCoords.get(i);
    	   targetY = yCoords.get(i+1);   
    	   currentBearing = calculateBearingToPoint(currentBearing, currentX, currentY, targetX, targetY);
    	   angle.add(currentBearing);
       }
       return angle;
       
    	
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
    	ArrayList<Point> dianji = new ArrayList<Point>();//凸包的点集
		ArrayList<Point> point = new ArrayList<Point>();//原点集列表
		point.addAll(points);
		if (points.size()<=3) // 点集中点小于等于3时，原来的点集就直接构成凸包，直接返回
			return points;
		Point first = point.get(0);//first是需要找的最左最下的一个初始点
		for (Point p : points) { // 找到最左最下的初始点
			if (p.x() < first.x()) {
				first = p;
			} else if (p.x() == first.x() && p.y() < first.y()) {
				first = p;
			}
		}
		dianji.add(first);//初始点加入集合
		point.remove(first);//从原始集合中去除初始点
		Point prePoint = first;
		int i = 0;
		do {
			if (i == 1)
				point.add(first);//再把原始点加入集合，作为循环的终止条件
			double zuixiaojiaodu = 360;//每次循环需要找的最小角度
			Point nextPoint = null;//初始下一个目标点为空
			double targetdis = 0;

			for (Point p : point) {

				double tempangle = calculateBearingToPoint(0, (int) prePoint.x(), (int) prePoint.y(), (int) p.x(),
(int) p.y());//计算转向角
				double tempdis = Math.pow(prePoint.x() - p.x(), 2) + Math.pow(prePoint.y() - p.y(), 2);//计算距离

				if (tempangle < zuixiaojiaodu ) {
					zuixiaojiaodu  = tempangle;
					nextPoint = p;
					targetdis = tempdis;//如果转向角比当前转向角更小，则符合要求，设置新的nextPoint和targetdis
				}
				else if (tempangle == zuixiaojiaodu  && tempdis > targetdis) {//转向角相同取远端点
					targetdis = tempdis;
					nextPoint = p;
				}
			}
			dianji.add(nextPoint);
			point.remove(nextPoint);
			prePoint = nextPoint;
			i++;
		} while (dianji.get(i) != first);
		
		points.removeAll(points);
		points.addAll(dianji);
		return points ;
		
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        int n = 4000;
        double angle = 91;
        for(int i = 0 ; i < n ; i ++) {
        	int t = i%10;
        	turtle.forward(i);
        	switch(t) {
        		case 0 : turtle.color(PenColor.BLACK); break;
        		case 1 : turtle.color(PenColor.BLUE); break;
        		case 2 : turtle.color(PenColor.CYAN); break;
        		case 3 : turtle.color(PenColor.GRAY); break;
        		case 4 : turtle.color(PenColor.GREEN); break;
        		case 5 : turtle.color(PenColor.MAGENTA); break;
        		case 6 : turtle.color(PenColor.ORANGE); break;
        		case 7: turtle.color(PenColor.PINK); break;
        		case 8 : turtle.color(PenColor.RED); break;
        		case 9 : turtle.color(PenColor.YELLOW); break;
        	}
        	turtle.turn(angle);
        }
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
        drawSquare(turtle, 40);
        turtle.draw();
        drawPersonalArt(turtle);


        // draw the window
    }

}
