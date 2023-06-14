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

public interface AlarmaService extends com.zeroc.Ice.Object
{
    void recibirNotificacionEscasezIngredientes(String iDing, int idMaq, String uuidACK, com.zeroc.Ice.Current current);

    void recibirNotificacionInsuficienciaMoneda(Moneda moneda, int idMaq, String uuidACK, com.zeroc.Ice.Current current);

    void recibirNotificacionEscasezSuministro(String idSumin, int idMaq, String uuidACK, com.zeroc.Ice.Current current);

    void recibirNotificacionAbastesimiento(int idMaq, String idInsumo, int cantidad, String uuidACK, com.zeroc.Ice.Current current);

    void recibirNotificacionMalFuncionamiento(int idMaq, String descri, String uuidACK, com.zeroc.Ice.Current current);

    /** @hidden */
    static final String[] _iceIds =
    {
        "::Ice::Object",
        "::servicios::AlarmaService"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::servicios::AlarmaService";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_recibirNotificacionEscasezIngredientes(AlarmaService obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_iDing;
        int iceP_idMaq;
        String iceP_uuidACK;
        iceP_iDing = istr.readString();
        iceP_idMaq = istr.readInt();
        iceP_uuidACK = istr.readString();
        inS.endReadParams();
        obj.recibirNotificacionEscasezIngredientes(iceP_iDing, iceP_idMaq, iceP_uuidACK, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_recibirNotificacionInsuficienciaMoneda(AlarmaService obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        Moneda iceP_moneda;
        int iceP_idMaq;
        String iceP_uuidACK;
        iceP_moneda = Moneda.ice_read(istr);
        iceP_idMaq = istr.readInt();
        iceP_uuidACK = istr.readString();
        inS.endReadParams();
        obj.recibirNotificacionInsuficienciaMoneda(iceP_moneda, iceP_idMaq, iceP_uuidACK, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_recibirNotificacionEscasezSuministro(AlarmaService obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_idSumin;
        int iceP_idMaq;
        String iceP_uuidACK;
        iceP_idSumin = istr.readString();
        iceP_idMaq = istr.readInt();
        iceP_uuidACK = istr.readString();
        inS.endReadParams();
        obj.recibirNotificacionEscasezSuministro(iceP_idSumin, iceP_idMaq, iceP_uuidACK, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_recibirNotificacionAbastesimiento(AlarmaService obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        int iceP_idMaq;
        String iceP_idInsumo;
        int iceP_cantidad;
        String iceP_uuidACK;
        iceP_idMaq = istr.readInt();
        iceP_idInsumo = istr.readString();
        iceP_cantidad = istr.readInt();
        iceP_uuidACK = istr.readString();
        inS.endReadParams();
        obj.recibirNotificacionAbastesimiento(iceP_idMaq, iceP_idInsumo, iceP_cantidad, iceP_uuidACK, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_recibirNotificacionMalFuncionamiento(AlarmaService obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        int iceP_idMaq;
        String iceP_descri;
        String iceP_uuidACK;
        iceP_idMaq = istr.readInt();
        iceP_descri = istr.readString();
        iceP_uuidACK = istr.readString();
        inS.endReadParams();
        obj.recibirNotificacionMalFuncionamiento(iceP_idMaq, iceP_descri, iceP_uuidACK, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "recibirNotificacionAbastesimiento",
        "recibirNotificacionEscasezIngredientes",
        "recibirNotificacionEscasezSuministro",
        "recibirNotificacionInsuficienciaMoneda",
        "recibirNotificacionMalFuncionamiento"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 1:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 2:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 3:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 4:
            {
                return _iceD_recibirNotificacionAbastesimiento(this, in, current);
            }
            case 5:
            {
                return _iceD_recibirNotificacionEscasezIngredientes(this, in, current);
            }
            case 6:
            {
                return _iceD_recibirNotificacionEscasezSuministro(this, in, current);
            }
            case 7:
            {
                return _iceD_recibirNotificacionInsuficienciaMoneda(this, in, current);
            }
            case 8:
            {
                return _iceD_recibirNotificacionMalFuncionamiento(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}
