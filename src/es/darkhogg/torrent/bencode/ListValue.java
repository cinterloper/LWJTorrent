/**
 * This package is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This package is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this package.  If not, see <http://www.gnu.org/licenses/>.
 */
package es.darkhogg.torrent.bencode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.darkhogg.torrent.bencode.Value;

/**
 * Wraps the bencode list type as a List of Values
 * 
 * @author Daniel Escoz
 * @version 1.0.0
 */
public final class ListValue extends Value<List<Value<?>>> {

	private List<Value<?>> value;
	
	/**
	 * Creates this object with an empty list
	 */
	public ListValue () {
		super( new ArrayList<Value<?>>() );
	}
	
	/**
	 * Creates this object with the given initial value
	 * 
	 * @param value Initial value
	 */
	public ListValue ( List<Value<?>> value ) {
		super( value );
	}
	
	@Override
	public List<Value<?>> getValue () {
		return Collections.unmodifiableList( value );
	}

	@Override
	public void setValue ( List<Value<?>> value ) {
		if ( value == null ) {
			throw new NullPointerException();
		}
		
		this.value = new ArrayList<Value<?>>( value );
	}
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder( "[\n" );
		
		for ( Value<?> val : value ) {
			sb.append( "  " );
			sb.append( val.toString().replace( "\n", "\n  " ) );
			sb.append( ",\n" );
		}
		
		return sb.append( ']' ).toString();
	}

	@Override
	public long getEncodedLength () {
		long childLength = 0;
		
		for ( Value<?> val : value ) {
			childLength += val.getEncodedLength();
		}
		
		return childLength + 2;
	}

}