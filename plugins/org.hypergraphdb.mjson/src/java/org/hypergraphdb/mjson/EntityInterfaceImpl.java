package org.hypergraphdb.mjson;

import org.mjson.Json;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGSearchResult;

/**
 * <p>
 * The default implementation {@link EntityInterfaceImpl} recognizes as entities
 * JSON objects that have an <code>entity</code> property of type <code>string</code>.
 * </p>
 */
public class EntityInterfaceImpl implements EntityInterface
{
    private String handleProperty = "hghandle";
    private String typeProperty = "entity";
    private boolean allowEntitiesInValuesFlag = false;
    private String primaryKey = null;

    /**
     * <p>Return true if the object has a property named <Code>entity</code> whose value is a string.</p>
     */
    public boolean isEntity(Json object)
    {
        Json p = object.at("entity");
        return p != null && p.isString();
    }

    public EntityInterfaceImpl entityHandleProperty(String handleProperty)
    {
        this.handleProperty = handleProperty;
        return this;
    }

    public String entityHandleProperty()
    {
        return handleProperty;
    }

    public EntityInterfaceImpl entityTypeProperty(String typeProperty)
    {
        this.typeProperty = typeProperty;
        return this;
    }

    public String entityTypeProperty()
    {
        return typeProperty;
    }
    
    public EntityInterfaceImpl allowEntitiesInImmutableValues(boolean f)
    {
        allowEntitiesInValuesFlag = f;
        return this;
    }

    public boolean allowEntitiesInImmutableValues()
    {
        return allowEntitiesInValuesFlag;
    }

    public String primaryKey()
    {
        return primaryKey;
    }

    public EntityInterfaceImpl primaryKey(String primaryKey)
    {
        this.primaryKey = primaryKey;
        return this;
    }

    public HGHandle lookupEntity(HyperNodeJson node, Json entity)
    {
        if (primaryKey != null && entity.has("primaryKey"))
        {
            HGSearchResult<HGHandle> rs = node.find(Json.object("entity", entity.at("entity"), 
                                                                primaryKey, entity.at(primaryKey)));
            try { return (rs.hasNext()) ? rs.next() : null; } finally { rs.close(); }
        }
        else
            return null;
    }
}