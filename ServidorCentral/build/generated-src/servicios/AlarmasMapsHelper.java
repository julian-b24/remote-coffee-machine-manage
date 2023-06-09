//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.8
//
// <auto-generated>
//
// Generated from file `CoffeMach.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package servicios;

/**
 * Helper class for marshaling/unmarshaling AlarmasMaps.
 **/
public final class AlarmasMapsHelper
{
    public static void write(com.zeroc.Ice.OutputStream ostr, java.util.List<servicios.alarma.AlarmaLogistica> v)
    {
        if(v == null)
        {
            ostr.writeSize(0);
        }
        else
        {
            ostr.writeSize(v.size());
            for(servicios.alarma.AlarmaLogistica elem : v)
            {
                ostr.writeSerializable(elem);
            }
        }
    }

    public static java.util.List<servicios.alarma.AlarmaLogistica> read(com.zeroc.Ice.InputStream istr)
    {
        final java.util.List<servicios.alarma.AlarmaLogistica> v;
        v = new java.util.ArrayList<servicios.alarma.AlarmaLogistica>();
        final int len0 = istr.readAndCheckSeqSize(1);
        for(int i0 = 0; i0 < len0; i0++)
        {
            servicios.alarma.AlarmaLogistica elem;
            elem = istr.readSerializable(servicios.alarma.AlarmaLogistica.class);
            v.add(elem);
        }
        return v;
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<java.util.List<servicios.alarma.AlarmaLogistica>> v)
    {
        if(v != null && v.isPresent())
        {
            write(ostr, tag, v.get());
        }
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.List<servicios.alarma.AlarmaLogistica> v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            AlarmasMapsHelper.write(ostr, v);
            ostr.endSize(pos);
        }
    }

    public static java.util.Optional<java.util.List<servicios.alarma.AlarmaLogistica>> read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            java.util.List<servicios.alarma.AlarmaLogistica> v;
            v = AlarmasMapsHelper.read(istr);
            return java.util.Optional.of(v);
        }
        else
        {
            return java.util.Optional.empty();
        }
    }
}
