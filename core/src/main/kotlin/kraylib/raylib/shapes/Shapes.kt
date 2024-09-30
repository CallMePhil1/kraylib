package kraylib.raylib.shapes

import kraylib.FFM.arena
import kraylib.ffm.Raylib
import kraylib.raylib.collections.NativeList
import kraylib.raylib.structs.Color
import kraylib.raylib.structs.Rectangle
import kraylib.raylib.structs.Texture2D
import kraylib.raylib.structs.Vector2

// NOTE: It can be useful when using basic shapes and one single font,
// defining a font char white rectangle would allow drawing everything in a single draw call

/** Set texture and rectangle to be used on shapes drawing */
fun setShapesTexture(texture: Texture2D, source: Rectangle) = Raylib.SetShapesTexture(texture.memorySegment, source.memorySegment)

// Basic shapes drawing functions
/** Draw a pixel */
fun drawPixel(posX: Int, posY: Int, color: Color) = Raylib.DrawPixel(posX, posY, color.memorySegment)
/** Draw a pixel (Vector version) */
fun drawPixelV(position: Vector2, color: Color) = Raylib.DrawPixelV(position.memorySegment, color.memorySegment)
/** Draw a line */
fun drawLine(startPosX: Int, startPosY: Int, endPosX: Int, endPosY: Int, color: Color) = Raylib.DrawLine(startPosX, startPosY, endPosX, endPosY, color.memorySegment)
/** Draw a line (using gl lines) */
fun drawLineV(startPos: Vector2, endPos: Vector2, color: Color) = Raylib.DrawLineV(startPos.memorySegment, endPos.memorySegment, color.memorySegment)
/** Draw a line (using triangles/quads) */
fun drawLineEx(startPos: Vector2, endPos: Vector2, thick: Float, color: Color) = Raylib.DrawLineEx(startPos.memorySegment, endPos.memorySegment, thick, color.memorySegment)
/** Draw lines sequence (using gl lines) */
fun drawLineStrip(points: NativeList<Vector2>, color: Color) = Raylib.DrawLineStrip(points.data, points.size, color.memorySegment)
/** Draw line segment cubic-bezier in-out interpolation */
fun drawLineBezier(startPos: Vector2, endPos: Vector2, thick: Float, color: Color) = Raylib.DrawLineBezier(startPos.memorySegment, endPos.memorySegment, thick, color.memorySegment)
/** Draw a color-filled circle */
fun drawCircle(centerX: Int, centerY: Int, radius: Float, color: Color) = Raylib.DrawCircle(centerX, centerY, radius, color.memorySegment)
/** Draw a piece of a circle */
fun drawCircleSector(center: Vector2, radius: Float, startAngle: Float, endAngle: Float, segments: Int, color: Color) = Raylib.DrawCircleSector(center.memorySegment, radius, startAngle, endAngle, segments, color.memorySegment)
/** Draw circle sector outline */
fun drawCircleSectorLines(center: Vector2, radius: Float, startAngle: Float, endAngle: Float, segments: Int, color: Color) = Raylib.DrawCircleSectorLines(center.memorySegment, radius, startAngle, endAngle, segments, color.memorySegment)
/** Draw a gradient-filled circle */
fun drawCircleGradient(centerX: Int, centerY: Int, radius: Float, color1: Color, color2: Color) = Raylib.DrawCircleGradient(centerX, centerY, radius, color1.memorySegment, color2.memorySegment)
/** Draw a color-filled circle (Vector version) */
fun drawCircleV(center: Vector2, radius: Float, color: Color) = Raylib.DrawCircleV(center.memorySegment, radius, color.memorySegment)
/** Draw circle outline */
fun drawCircleLines(centerX: Int, centerY: Int, radius: Float, color: Color) = Raylib.DrawCircleLines(centerX, centerY, radius, color.memorySegment)
/** Draw circle outline (Vector version) */
fun drawCircleLinesV(center: Vector2, radius: Float, color: Color) = Raylib.DrawCircleLinesV(center.memorySegment, radius, color.memorySegment)
/** Draw ellipse */
fun drawEllipse(centerX: Int, centerY: Int, radiusH: Float, radiusV: Float, color: Color) = Raylib.DrawEllipse(centerX, centerY, radiusH, radiusV, color.memorySegment)
/** Draw ellipse outline */
fun drawEllipseLines(centerX: Int, centerY: Int, radiusH: Float, radiusV: Float, color: Color) = Raylib.DrawEllipseLines(centerX, centerY, radiusH, radiusV, color.memorySegment)
/** Draw ring */
fun drawRing(center: Vector2, innerRadius: Float, outerRadius: Float, startAngle: Float, endAngle: Float, segments: Int, color: Color) = Raylib.DrawRing(center.memorySegment, innerRadius, outerRadius, startAngle, endAngle, segments, color.memorySegment)
/** Draw ring outline */
fun drawRingLines(center: Vector2, innerRadius: Float, outerRadius: Float, startAngle: Float, endAngle: Float, segments: Int, color: Color) = Raylib.DrawRingLines(center.memorySegment, innerRadius, outerRadius, startAngle, endAngle, segments, color.memorySegment)
/** Draw a color-filled rectangle */
fun drawRectangle(posX: Int, posY: Int, width: Int, height: Int, color: Color) = Raylib.DrawRectangle(posX, posY, width, height, color.memorySegment)
/** Draw a color-filled rectangle (Vector version) */
fun drawRectangleV(position: Vector2, size: Vector2, color: Color) = Raylib.DrawRectangleV(position.memorySegment, size.memorySegment, color.memorySegment)
/** Draw a color-filled rectangle */
fun drawRectangleRec(rec: Rectangle, color: Color) = Raylib.DrawRectangleRec(rec.memorySegment, color.memorySegment)
/** Draw a color-filled rectangle with pro parameters */
fun drawRectanglePro(rec: Rectangle, origin: Vector2, rotation: Float, color: Color) = Raylib.DrawRectanglePro(rec.memorySegment, origin.memorySegment, rotation, color.memorySegment)
/** Draw a vertical-gradient-filled rectangle */
fun drawRectangleGradientV(posX: Int, posY: Int, width: Int, height: Int, color1: Color, color2: Color) = Raylib.DrawRectangleGradientV(posX, posY, width, height, color1.memorySegment, color2.memorySegment)
/** Draw a horizontal-gradient-filled rectangle */
fun drawRectangleGradientH(posX: Int, posY: Int, width: Int, height: Int, color1: Color, color2: Color) = Raylib.DrawRectangleGradientH(posX, posY, width, height, color1.memorySegment, color2.memorySegment)
/** Draw a gradient-filled rectangle with custom vertex colors */
fun drawRectangleGradientEx(rec: Rectangle, col1: Color, col2: Color, col3: Color, col4: Color) = Raylib.DrawRectangleGradientEx(rec.memorySegment, col1.memorySegment, col2.memorySegment, col3.memorySegment, col4.memorySegment)
/** Draw rectangle outline */
fun drawRectangleLines(posX: Int, posY: Int, width: Int, height: Int, color: Color) = Raylib.DrawRectangleLines(posX, posY, width, height, color.memorySegment)
/** Draw rectangle outline with extended parameters */
fun drawRectangleLinesEx(rec: Rectangle, lineThick: Float, color: Color) = Raylib.DrawRectangleLinesEx(rec.memorySegment, lineThick, color.memorySegment)
/** Draw rectangle with rounded edges */
fun drawRectangleRounded(rec: Rectangle, roundness: Float, segments: Int, color: Color) = Raylib.DrawRectangleRounded(rec.memorySegment, roundness, segments, color.memorySegment)
/** Draw rectangle with rounded edges outline */
fun drawRectangleRoundedLines(rec: Rectangle, roundness: Float, segments: Int, lineThick: Float, color: Color) = Raylib.DrawRectangleRoundedLines(rec.memorySegment, roundness, segments, lineThick, color.memorySegment)
/** Draw a color-filled triangle (vertex in counter-clockwise order!) */
fun drawTriangle(v1: Vector2, v2: Vector2, v3: Vector2, color: Color) = Raylib.DrawTriangle(v1.memorySegment, v2.memorySegment, v3.memorySegment, color.memorySegment)
/** Draw triangle outline (vertex in counter-clockwise order!) */
fun drawTriangleLines(v1: Vector2, v2: Vector2, v3: Vector2, color: Color) = Raylib.DrawTriangleLines(v1.memorySegment, v2.memorySegment, v3.memorySegment, color.memorySegment)
/** Draw a triangle fan defined by points (first vertex is the center) */
fun drawTriangleFan(points: NativeList<Vector2>, color: Color) = Raylib.DrawTriangleFan(points.data, points.size, color.memorySegment)
/** Draw a triangle strip defined by points */
fun drawTriangleStrip(points: NativeList<Vector2>, color: Color) = Raylib.DrawTriangleStrip(points.data, points.size, color.memorySegment)
/** Draw a regular polygon (Vector version) */
fun drawPoly(center: Vector2, sides: Int, radius: Float, rotation: Float, color: Color) = Raylib.DrawPoly(center.memorySegment, sides, radius, rotation, color.memorySegment)
/** Draw a polygon outline of n sides */
fun drawPolyLines(center: Vector2, sides: Int, radius: Float, rotation: Float, color: Color) = Raylib.DrawPolyLines(center.memorySegment, sides, radius, rotation, color.memorySegment)
/** Draw a polygon outline of n sides with extended parameters */
fun drawPolyLinesEx(center: Vector2, sides: Int, radius: Float, rotation: Float, lineThick: Float, color: Color) = Raylib.DrawPolyLinesEx(center.memorySegment, sides, radius, rotation, lineThick, color.memorySegment)

// Splines drawing functions
/** Draw spline: Linear, minimum 2 points */
fun drawSplineLinear(points: NativeList<Vector2>, thick: Float, color: Color) = Raylib.DrawSplineLinear(points.data, points.size, thick, color.memorySegment)
/** Draw spline: B-Spline, minimum 4 points */
fun drawSplineBasis(points: NativeList<Vector2>, thick: Float, color: Color) = Raylib.DrawSplineBasis(points.data, points.size, thick, color.memorySegment)
/** Draw spline: Catmull-Rom, minimum 4 points */
fun drawSplineCatmullRom(points: NativeList<Vector2>, thick: Float, color: Color) = Raylib.DrawSplineCatmullRom(points.data, points.size, thick, color.memorySegment)
/** Draw spline: Quadratic Bezier, minimum 3 points (1 control point): [p1, c2, p3, c4...] */
fun drawSplineBezierQuadratic(points: NativeList<Vector2>, thick: Float, color: Color) = Raylib.DrawSplineBezierQuadratic(points.data, points.size, thick, color.memorySegment)
/** Draw spline: Cubic Bezier, minimum 4 points (2 control points): [p1, c2, c3, p4, c5, c6...] */
fun drawSplineBezierCubic(points: NativeList<Vector2>, thick: Float, color: Color) = Raylib.DrawSplineBezierCubic(points.data, points.size, thick, color.memorySegment)
/** Draw spline segment: Linear, 2 points */
fun drawSplineSegmentLinear(p1: Vector2, p2: Vector2, thick: Float, color: Color) = Raylib.DrawSplineSegmentLinear(p1.memorySegment, p2.memorySegment, thick, color.memorySegment)
/** Draw spline segment: B-Spline, 4 points */
fun drawSplineSegmentBasis(p1: Vector2, p2: Vector2, p3: Vector2, p4: Vector2, thick: Float, color: Color) = Raylib.DrawSplineSegmentBasis(p1.memorySegment, p2.memorySegment, p3.memorySegment, p4.memorySegment, thick, color.memorySegment)
/** Draw spline segment: Catmull-Rom, 4 points */
fun drawSplineSegmentCatmullRom(p1: Vector2, p2: Vector2, p3: Vector2, p4: Vector2, thick: Float, color: Color) = Raylib.DrawSplineSegmentCatmullRom(p1.memorySegment, p2.memorySegment, p3.memorySegment, p4.memorySegment, thick, color.memorySegment)
/** Draw spline segment: Quadratic Bezier, 2 points, 1 control point */
fun drawSplineSegmentBezierQuadratic(p1: Vector2, c2: Vector2, p3: Vector2, thick: Float, color: Color) = Raylib.DrawSplineSegmentBezierQuadratic(p1.memorySegment, c2.memorySegment, p3.memorySegment, thick, color.memorySegment)
/** Draw spline segment: Cubic Bezier, 2 points, 2 control points */
fun drawSplineSegmentBezierCubic(p1: Vector2, c2: Vector2, c3: Vector2, p4: Vector2, thick: Float, color: Color) = Raylib.DrawSplineSegmentBezierCubic(p1.memorySegment, c2.memorySegment, c3.memorySegment, p4.memorySegment, thick, color.memorySegment)

// Spline segment poevaluation: Int functions, for a given t [0.0f .. 1.0f]
/** Get (evaluate) spline point: Linear */
fun getSplinePointLinear(startPos: Vector2, endPos: Vector2, t: Float) = Raylib.GetSplinePointLinear(arena, startPos.memorySegment, endPos.memorySegment, t)
/** Get (evaluate) spline point: B-Spline */
fun getSplinePointBasis(p1: Vector2, p2: Vector2, p3: Vector2, p4: Vector2, t: Float) = Raylib.GetSplinePointBasis(arena, p1.memorySegment, p2.memorySegment, p3.memorySegment, p4.memorySegment, t)
/** Get (evaluate) spline point: Catmull-Rom */
fun getSplinePointCatmullRom(p1: Vector2, p2: Vector2, p3: Vector2, p4: Vector2, t: Float) = Raylib.GetSplinePointCatmullRom(arena, p1.memorySegment, p2.memorySegment, p3.memorySegment, p4.memorySegment, t)
/** Get (evaluate) spline point: Quadratic Bezier */
fun getSplinePointBezierQuad(p1: Vector2, c2: Vector2, p3: Vector2, t: Float) = Raylib.GetSplinePointBezierQuad(arena, p1.memorySegment, c2.memorySegment, p3.memorySegment, t)
/** Get (evaluate) spline point: Cubic Bezier */
fun getSplinePointBezierCubic(p1: Vector2, c2: Vector2, c3: Vector2, p4: Vector2, t: Float) = Raylib.GetSplinePointBezierCubic(arena, p1.memorySegment, c2.memorySegment, c3.memorySegment, p4.memorySegment, t)

// Basic shapes collision detection functions
/** Check collision between two rectangles */
fun checkCollisionRecs(rec1: Rectangle, rec2: Rectangle) = Raylib.CheckCollisionRecs(rec1.memorySegment, rec2.memorySegment)
/** Check collision between two circles */
fun checkCollisionCircles(center1: Vector2, radius1: Float, center2: Vector2, radius2: Float) = Raylib.CheckCollisionCircles(center1.memorySegment, radius1, center2.memorySegment, radius2)
/** Check collision between circle and rectangle */
fun checkCollisionCircleRec(center: Vector2, radius: Float, rec: Rectangle) = Raylib.CheckCollisionCircleRec(center.memorySegment, radius, rec.memorySegment)
/** Check if pois: Int inside rectangle */
fun checkCollisionPointRec(point: Vector2, rec: Rectangle) = Raylib.CheckCollisionPointRec(point.memorySegment, rec.memorySegment)
/** Check if pois: Int inside circle */
fun checkCollisionPointCircle(point: Vector2, center: Vector2, radius: Float) = Raylib.CheckCollisionPointCircle(point.memorySegment, center.memorySegment, radius)
/** Check if pois: Int inside a triangle */
fun checkCollisionPointTriangle(point: Vector2, p1: Vector2, p2: Vector2, p3: Vector2) = Raylib.CheckCollisionPointTriangle(point.memorySegment, p1.memorySegment, p2.memorySegment, p3.memorySegment)
/** Check if pois: Int within a polygon described by array of vertices */
fun checkCollisionPointPoly(point: Vector2, points: NativeList<Vector2>) = Raylib.CheckCollisionPointPoly(point.memorySegment, points.data, points.size)
/** Check the collision between two lines defined by two points each, returns collision poby: Int reference */
fun checkCollisionLines(startPos1: Vector2, endPos1: Vector2, startPos2: Vector2, endPos2: Vector2, collisionPoint: NativeList<Vector2> ) = Raylib.CheckCollisionLines(startPos1.memorySegment, endPos1.memorySegment, startPos2.memorySegment, endPos2.memorySegment, collisionPoint.data)
/** Check if pobelongs: Int to line created between two points [p1] and [p2] with defined margin in pixels [threshold] */
fun checkCollisionPointLine(point: Vector2, p1: Vector2, p2: Vector2, threshold: Int) = Raylib.CheckCollisionPointLine(point.memorySegment, p1.memorySegment, p2.memorySegment, threshold)
/** Get collision rectangle for two rectangles collision */
fun getCollisionRec(rec1: Rectangle, rec2: Rectangle) = Raylib.GetCollisionRec(arena, rec1.memorySegment, rec2.memorySegment)