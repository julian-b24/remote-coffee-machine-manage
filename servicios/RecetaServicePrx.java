//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.8
//
// <auto-generated>
//
// Generated from file `coffeMach.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package servicios;

public interface RecetaServicePrx extends com.zeroc.Ice.ObjectPrx
{
    default String[] consultarIngredientes()
    {
        return consultarIngredientes(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String[] consultarIngredientes(java.util.Map<String, String> context)
    {
        return _iceI_consultarIngredientesAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<String[]> consultarIngredientesAsync()
    {
        return _iceI_consultarIngredientesAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<String[]> consultarIngredientesAsync(java.util.Map<String, String> context)
    {
        return _iceI_consultarIngredientesAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<String[]> _iceI_consultarIngredientesAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<String[]> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "consultarIngredientes", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     String[] ret;
                     ret = istr.readStringSeq();
                     return ret;
                 });
        return f;
    }

    default String[] consultarRecetas()
    {
        return consultarRecetas(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String[] consultarRecetas(java.util.Map<String, String> context)
    {
        return _iceI_consultarRecetasAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<String[]> consultarRecetasAsync()
    {
        return _iceI_consultarRecetasAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<String[]> consultarRecetasAsync(java.util.Map<String, String> context)
    {
        return _iceI_consultarRecetasAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<String[]> _iceI_consultarRecetasAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<String[]> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "consultarRecetas", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     String[] ret;
                     ret = istr.readStringSeq();
                     return ret;
                 });
        return f;
    }

    default String[] consultarProductos()
    {
        return consultarProductos(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String[] consultarProductos(java.util.Map<String, String> context)
    {
        return _iceI_consultarProductosAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<String[]> consultarProductosAsync()
    {
        return _iceI_consultarProductosAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<String[]> consultarProductosAsync(java.util.Map<String, String> context)
    {
        return _iceI_consultarProductosAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<String[]> _iceI_consultarProductosAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<String[]> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "consultarProductos", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     String[] ret;
                     ret = istr.readStringSeq();
                     return ret;
                 });
        return f;
    }

    default void definirProducto(String nombre, int precio, java.util.Map<java.lang.String, java.lang.Integer> ingredientes)
    {
        definirProducto(nombre, precio, ingredientes, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void definirProducto(String nombre, int precio, java.util.Map<java.lang.String, java.lang.Integer> ingredientes, java.util.Map<String, String> context)
    {
        _iceI_definirProductoAsync(nombre, precio, ingredientes, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> definirProductoAsync(String nombre, int precio, java.util.Map<java.lang.String, java.lang.Integer> ingredientes)
    {
        return _iceI_definirProductoAsync(nombre, precio, ingredientes, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> definirProductoAsync(String nombre, int precio, java.util.Map<java.lang.String, java.lang.Integer> ingredientes, java.util.Map<String, String> context)
    {
        return _iceI_definirProductoAsync(nombre, precio, ingredientes, context, false);
    }

    /**
     * @hidden
     * @param iceP_nombre -
     * @param iceP_precio -
     * @param iceP_ingredientes -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_definirProductoAsync(String iceP_nombre, int iceP_precio, java.util.Map<java.lang.String, java.lang.Integer> iceP_ingredientes, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "definirProducto", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_nombre);
                     ostr.writeInt(iceP_precio);
                     MapStrIntHelper.write(ostr, iceP_ingredientes);
                 }, null);
        return f;
    }

    default void borrarReceta(int cod)
    {
        borrarReceta(cod, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void borrarReceta(int cod, java.util.Map<String, String> context)
    {
        _iceI_borrarRecetaAsync(cod, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> borrarRecetaAsync(int cod)
    {
        return _iceI_borrarRecetaAsync(cod, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> borrarRecetaAsync(int cod, java.util.Map<String, String> context)
    {
        return _iceI_borrarRecetaAsync(cod, context, false);
    }

    /**
     * @hidden
     * @param iceP_cod -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_borrarRecetaAsync(int iceP_cod, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "borrarReceta", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeInt(iceP_cod);
                 }, null);
        return f;
    }

    default void definirRecetaIngrediente(int idReceta, int idIngrediente, int valor)
    {
        definirRecetaIngrediente(idReceta, idIngrediente, valor, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void definirRecetaIngrediente(int idReceta, int idIngrediente, int valor, java.util.Map<String, String> context)
    {
        _iceI_definirRecetaIngredienteAsync(idReceta, idIngrediente, valor, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> definirRecetaIngredienteAsync(int idReceta, int idIngrediente, int valor)
    {
        return _iceI_definirRecetaIngredienteAsync(idReceta, idIngrediente, valor, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> definirRecetaIngredienteAsync(int idReceta, int idIngrediente, int valor, java.util.Map<String, String> context)
    {
        return _iceI_definirRecetaIngredienteAsync(idReceta, idIngrediente, valor, context, false);
    }

    /**
     * @hidden
     * @param iceP_idReceta -
     * @param iceP_idIngrediente -
     * @param iceP_valor -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_definirRecetaIngredienteAsync(int iceP_idReceta, int iceP_idIngrediente, int iceP_valor, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "definirRecetaIngrediente", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeInt(iceP_idReceta);
                     ostr.writeInt(iceP_idIngrediente);
                     ostr.writeInt(iceP_valor);
                 }, null);
        return f;
    }

    default String registrarReceta(String nombre, int precio)
    {
        return registrarReceta(nombre, precio, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String registrarReceta(String nombre, int precio, java.util.Map<String, String> context)
    {
        return _iceI_registrarRecetaAsync(nombre, precio, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> registrarRecetaAsync(String nombre, int precio)
    {
        return _iceI_registrarRecetaAsync(nombre, precio, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> registrarRecetaAsync(String nombre, int precio, java.util.Map<String, String> context)
    {
        return _iceI_registrarRecetaAsync(nombre, precio, context, false);
    }

    /**
     * @hidden
     * @param iceP_nombre -
     * @param iceP_precio -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.String> _iceI_registrarRecetaAsync(String iceP_nombre, int iceP_precio, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.String> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "registrarReceta", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_nombre);
                     ostr.writeInt(iceP_precio);
                 }, istr -> {
                     String ret;
                     ret = istr.readString();
                     return ret;
                 });
        return f;
    }

    default String registrarIngrediente(String nombre)
    {
        return registrarIngrediente(nombre, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String registrarIngrediente(String nombre, java.util.Map<String, String> context)
    {
        return _iceI_registrarIngredienteAsync(nombre, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> registrarIngredienteAsync(String nombre)
    {
        return _iceI_registrarIngredienteAsync(nombre, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> registrarIngredienteAsync(String nombre, java.util.Map<String, String> context)
    {
        return _iceI_registrarIngredienteAsync(nombre, context, false);
    }

    /**
     * @hidden
     * @param iceP_nombre -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.String> _iceI_registrarIngredienteAsync(String iceP_nombre, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.String> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "registrarIngrediente", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_nombre);
                 }, istr -> {
                     String ret;
                     ret = istr.readString();
                     return ret;
                 });
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static RecetaServicePrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), RecetaServicePrx.class, _RecetaServicePrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static RecetaServicePrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), RecetaServicePrx.class, _RecetaServicePrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static RecetaServicePrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), RecetaServicePrx.class, _RecetaServicePrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static RecetaServicePrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), RecetaServicePrx.class, _RecetaServicePrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static RecetaServicePrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, RecetaServicePrx.class, _RecetaServicePrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static RecetaServicePrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, RecetaServicePrx.class, _RecetaServicePrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default RecetaServicePrx ice_context(java.util.Map<String, String> newContext)
    {
        return (RecetaServicePrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default RecetaServicePrx ice_adapterId(String newAdapterId)
    {
        return (RecetaServicePrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default RecetaServicePrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (RecetaServicePrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default RecetaServicePrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (RecetaServicePrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default RecetaServicePrx ice_invocationTimeout(int newTimeout)
    {
        return (RecetaServicePrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default RecetaServicePrx ice_connectionCached(boolean newCache)
    {
        return (RecetaServicePrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default RecetaServicePrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (RecetaServicePrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default RecetaServicePrx ice_secure(boolean b)
    {
        return (RecetaServicePrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default RecetaServicePrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (RecetaServicePrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default RecetaServicePrx ice_preferSecure(boolean b)
    {
        return (RecetaServicePrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default RecetaServicePrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (RecetaServicePrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default RecetaServicePrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (RecetaServicePrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default RecetaServicePrx ice_collocationOptimized(boolean b)
    {
        return (RecetaServicePrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default RecetaServicePrx ice_twoway()
    {
        return (RecetaServicePrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default RecetaServicePrx ice_oneway()
    {
        return (RecetaServicePrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default RecetaServicePrx ice_batchOneway()
    {
        return (RecetaServicePrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default RecetaServicePrx ice_datagram()
    {
        return (RecetaServicePrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default RecetaServicePrx ice_batchDatagram()
    {
        return (RecetaServicePrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default RecetaServicePrx ice_compress(boolean co)
    {
        return (RecetaServicePrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default RecetaServicePrx ice_timeout(int t)
    {
        return (RecetaServicePrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default RecetaServicePrx ice_connectionId(String connectionId)
    {
        return (RecetaServicePrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default RecetaServicePrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (RecetaServicePrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::servicios::RecetaService";
    }
}
