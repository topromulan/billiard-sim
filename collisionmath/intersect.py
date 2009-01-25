
##########################################################################
# Setting two circles equations equal to one another to calculate a line #
##########################################################################

#Example

# circle 1 radius 3 at 5,5.  (x - 5)^2 + (y - 5)^2 = 3^2

# circle 2 radius 4 at 8,8.  (x - 8)^2 + (y - 8)^2 = 4^2

# (x-5)^2 + (y-5)^2 - 9 = (x-8)^2 + (y-8)^2 - 16

# x^2-10x+25 + y^2-10y+25 - 9 = x^2-16x+64 + y^2-16y+64 - 16
# x^2-10x + y^2-10y +41 = x^2-16x + y^2-16y + 112
# -10x + -10y +41 = -16x + -16y + 112
# 6x + 41 = -6y + 112
# 6x = -6y + 71
# y = -x - (71/6)

#Generalize:

# (x-h1)^2 + (y-v1)^2 - r1^2 = (x-h2)^2 + (y-v2)^2 - r2^2
# x^2 -2h1x +h1^2 + y^2 -2v1y +v1^2 - r1^2 = x^2 -2h2x +h2^2 + y^2 -2v2y +v2^2 - r2^2
# -2h1x +h1^2 -2v1y +v1^2 -r1^2 = -2h2x +h2^2 -2v2y +v2^2 -r2^2

# -2h1x +h1^2 -2v1y +v1^2 -r1^2 = -2h2x +h2^2 -2v2y +v2^2 -r2^2
# xxxxx ccccc yyyyy ccccc ccccc   xxxxx ccccc yyyyy ccccc ccccc
# +2h1x -h1^2       -v1^2 +r1^2               +2v2y

# -2v1y +2v2y = -2h2x +h2^2 +v2^2 -r2^2 +2h1x -h1^2 -v1^2 +r1^2
# -2v1y +2v2y = -2h2x +2h1x +h2^2 +v2^2 -h1^2 -v1^2 +r1^2 -r2^2 

# -2(v1 -v2)y = 2(h1 -h2)x +h2^2 +v2^2 -h1^2 -v1^2 +r1^2 -r2^2 

#### GENERAL FORM:
# y = -[(h1 -h2)/(v1 -v2)]x + (+h2^2 +v2^2 -h1^2 -v1^2 +r1^2 -r2^2)/[(-2)(v1 -v2)]
#
# .
#. .:
#
# slope = -[(h1-h2)/(v1-v2)]
# y int = (h2^2+v2^2-h1^2-v1^2+r1^2-r2^2) / (+2v2-2v1)

# test: 
#  h1 = 5	v1 = 5		r1 = 3
#  h2 = 8	v2 = 8		r2 = 4

# m = -[(5-8)/(5-8)] = -1
# b = (8^2+8^2-5^2-5^2+3^2-4^2) / (+16-10) = (128-50+9-16) / 6 = 71 / 6


#########

# y = mx + b
# y = v +/- sqrt(-(x-h)^2 + r^2)

# p = +1 or -1

# mx + b = v + p*sqrt(-(x-h)^2 + r^2)
# mx + (b - v) = p*sqrt(-(x-h)^2 + r^2)
# (mx + (b-v))^2 = 1(-(x-h)^2 + r^2)
# m^2x^2 + 2m(b-v)x + (b-v)^2 = -(x-h)^2 + r^2
# m^2x^2 + 2m(b-v)x + (b-v)^2 = -(x^2 - 2xh +h^2) + r^2
# m^2x^2 + 2m(b-v)x + (b-v)^2 = -x^2 + 2xh - h^2 + r^2
#                               +x^2 - 2xh + h^2 - r^2
# (m^2 + 1)x^2 + (2m(b-v) -2h)x + [(b-v)^2 + h^2 - r^2] = 0

# we can use the quadratic formula from there, and:

# a = m^2 + 1
# b = 2m(b-v) - 2h
# c = (b-v)^2 + h^2 - r^2

# Note: b on the rsides is the line's y intercept

# if it yields real answers then plug them into the line equation for a quick answer
#  where the collision point was or the circles' equations to calculate where the 
#  circle struck - (would require comparing answers with line answer and discard one)



class Line:
	# y = mx + b

	def __init__(self, slope, yint):
		self.m = slope
		self.b = yint

class Circle:
	# (x-h)^2 + (y-v)^2 = r^2

	def __init__(self, horiz, vert, radius):
		self.h = horiz
		self.v = vert
		self.r = radius

c1 = Circle(4, 4, 2)
c2 = Circle(3, 3, 2)


# slope = -[(h1-h2)/(v1-v2)]
# y int = (h2^2+v2^2-h1^2-v1^2+r1^2-r2^2) / (+2v2-2v1)

slope = -( (c1.h - c2.h) / (c1.v - c2.v) )
yint = (c2.h**2 + c2.v**2 - c1.h**2 - c1.v**2 + c1.r**2 - c2.r**2) / (2*c2.v - 2*c1.v)
#        3        3        4        4        2        2            3        4
#      ( 9     + 9      - 16     - 16     + 4      - 4     ) / (6 - 8)
#      ( 18 - 32 ) / -2
#      -14 / -2 = 7

l = Line(slope, yint)

print "# Circle 1: # (x-%.3f)^2 + (y-%.3f)^2 = %.3f^2" % (c1.h, c1.v, c1.r)
print "# Circle 2: # (x-%.3f)^2 + (y-%.3f)^2 = %.3f^2" % (c2.h, c2.v, c2.r)
print "# Line:     # y = %.3fx + %.3f" % (l.m, l.b)
print "#############"


# a = m^2 + 1
# b = 2m(b-v) - 2h
# c = (b-v)^2 + h^2 - r^2

# solve using C1
a = l.m**2 + 1
b = 2 * l.m * (l.b-c1.v) - 2*c1.h
c = (l.b - c1.v)**2 + c1.h**2 - c1.r**2

# with C2 (s/b same)
##a = l.m**2 + 1
##b = 2 * l.m * (l.b-c2.v) - 2*c2.h
##c = (l.b - c2.v)**2 + c2.h**2 - c2.r**2

# x = (-b +/- sqrt(b^2 - 4ac)) / 2a

# the correct parabola for paper example:
#a = 2
#b = -14
#c = 21

print "# Parabola: # y = %.3fx^2 + %.3fx + %.3f" % (a,b,c)

try:
	x1 = (-b + (b**2 - 4*a*c)**0.5) / (2*a)
	x1s = "%.3f" % x1
except ValueError:
	x1 = x1s = "imaginary"

print "# x1 is %s" % x1s

try:
	x2 = (-b - (b**2 - 4*a*c)**0.5) / (2*a)
	x2s = "%.3f" % x2
except ValueError:
	x2 = x2s = "imaginary"

print "# x2 is %s" % x2s


print "#############"

# now there has got to be a more elegant way to check if it's a number
if ( x1.__class__ != type('asdf') ):
	print "#############"
	print "# Result x1:# x1 = %.3f" % x1
	print "#in Line:   # %.3f" % (l.m * x1 + l.b)
	print "#in C1 (+): # %.3f" % ( c1.v + (-(x1 - c1.h)**2 + c1.r**2)**0.5 )
	print "#in C1 (-): # %.3f" % ( c1.v - (-(x1 - c1.h)**2 + c1.r**2)**0.5 )
if ( x2.__class__ != type('asdf') ):
	print "#############"
	print "# Result x2:# x2 = %.3f" % x2
	print "#in Line:   # %.3f" % (l.m * x2 + l.b)
	print "#in C2 (+): # %.3f" % ( c1.v + (-(x2 - c1.h)**2 + c1.r**2)**0.5 )
	print "#in C2 (-): # %.3f" % ( c1.v - (-(x2 - c1.h)**2 + c1.r**2)**0.5 )
	












