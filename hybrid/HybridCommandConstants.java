/*

Copyright 2002 Graham Ritchie <grr@dcs.st-and.ac.uk>

This file is part of Intellego.

Intellego is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

Intellego is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Intellego; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/

package hybrid;

/**
* Provides command constants for the HybridRCX, and the HybridOnBoard classes
*
* @author Graham Ritchie
*/
public interface HybridCommandConstants
{
	static final int MOVE_FORWARD=1;
	static final int MOVE_BACKWARD=2;
	static final int MOVE_RIGHT=3;
	static final int MOVE_LEFT=4;
	static final int STOP_MOVING=5;
	static final int BEEP=6;
	static final int GET_S1=7;
	static final int GET_S2=8;
	static final int GET_S3=9;
}
