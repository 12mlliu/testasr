/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.8
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.midea.asr;

public class PocketSphinxJNI {
  public final static native void Hypothesis_hypstr_set(long jarg1, Hypothesis jarg1_, String jarg2);
  public final static native String Hypothesis_hypstr_get(long jarg1, Hypothesis jarg1_);
  public final static native void Hypothesis_bestScore_set(long jarg1, Hypothesis jarg1_, int jarg2);
  public final static native int Hypothesis_bestScore_get(long jarg1, Hypothesis jarg1_);
  public final static native void Hypothesis_prob_set(long jarg1, Hypothesis jarg1_, int jarg2);
  public final static native int Hypothesis_prob_get(long jarg1, Hypothesis jarg1_);
  public final static native long new_Hypothesis(String jarg1, int jarg2, int jarg3);
  public final static native void delete_Hypothesis(long jarg1);
  public final static native long new_Decoder__SWIG_0();
  public final static native long new_Decoder__SWIG_1(long jarg1, Config jarg1_);
  public final static native void delete_Decoder(long jarg1);
  public final static native long Decoder_defaultConfig();
  public final static native void Decoder_startUtt(long jarg1, Decoder jarg1_);
  public final static native int Decoder_processRaw(long jarg1, Decoder jarg1_, short[] jarg2, long jarg3, boolean jarg4, boolean jarg5);
  public final static native void Decoder_endUtt(long jarg1, Decoder jarg1_);
  public final static native void Decoder_setRawdataSize(long jarg1, Decoder jarg1_, long jarg2);
  public final static native short[] Decoder_getRawdata(long jarg1, Decoder jarg1_);
  public final static native long Decoder_hyp(long jarg1, Decoder jarg1_);
}
