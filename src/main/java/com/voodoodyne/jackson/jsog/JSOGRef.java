package com.voodoodyne.jackson.jsog;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * This object lets us persuade jackson into serializing JSOG structures. It is the id generated,
 * and a special serializer knows how to convert it to an @id or @ref as appropriate.
 *
 * @author Jeff Schnitzer <jeff@infohazard.org>
 */
@JsonSerialize(using=JSOGRefSerializer.class)
@JsonDeserialize(using=JSOGRefDeserializer.class)
public class JSOGRef
{
	/** */
	public static final String REF_KEY = "@ref";

	/** The stringified numeric */
	@JsonProperty(REF_KEY)
	public String ref;

	/**
	 * A flag we use to determine if this ref has already been serialized. Because jackson calls the same
	 * code for serializing both ids and refs, we simply assume the first use is an id and all subsequent
	 * uses are refs.
	 */
	public transient boolean used;

	/** */
	public JSOGRef(String val) {
		ref = val;
	}

	/** */
	public JSOGRef(int val) {
		this(Integer.toString(val));
	}
}