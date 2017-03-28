package org.hypergraphdb.mjson;

import org.mjson.Json;

public class JsonNodeException extends RuntimeException
{
    private Json data;

    public JsonNodeException(String msg)
    {
        super(msg);
    }

    public JsonNodeException(String msg, Json data)
    {
        super(msg);
        this.data = data;
    }

    public Json data()
    {
        return data;
    }
}