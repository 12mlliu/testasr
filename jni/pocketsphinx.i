/*pocketsphonx.i by meili
*2017/01/05
*part of original pocketsphinx.i
*/

#if SWIGJAVA
%module PocketSphinx
#endif

/*not sure, maybe can automatically generated some file? .java .class*/
%feature("autodoc","1");

/*question1: look typemaps.i and iterators.i and sphinxbase.i*/
%include typemaps.i
%include iterators.i
%import sphinxbase.i

/*the two typedef for Config and Decoder can be merge as one ? try*/
%{
typedef cmd_ln_t Config;
%}

%begin %{

#ifndef __cplusplus
typedef int bool;
#define true 1
#define false 0
#endif

#include <pocketsphinx.h>
typedef ps_decoder_t Decoder;
%}

/*inline can creat a new struct 
for Hypothesis output string
three items */

/*question2: what why */
typedef struct {} Decoder;

/*Decoder class
contain the struct instance and the fuction needed to decoder*/
%extend Decoder {
        /*the first function means if there is no config input
        the Decoder will also be initialized*/
        Decoder(int *errcode) {
                Decoder *d = ps_init(cmd_ln_init(NULL,ps_args(),FALSE,NULL));
                *errcode = d ? 0 : -1;
                return d;
        }
        /*initialize Decoder from given config parameters*/
        Decoder(Config* config, int *errcode){
                Decoder *d = ps_init(config);
                *errcode = d ? 0 : -1;
                return d;
        }
        /*when not use the memory will be free
        $self just like the class in python, $self is the struct instance*/
        ~Decoder() {
                ps_free($self);
        }
        /*newobject for some function that malloc memory
        when use newobject, swig can free the memory when not use
        to avoid memory leak*/
        %newobject default_config;
        static Config *default_config() {
                return cmd_ln_parse_r(NULL,ps_args(),0,NULL,FALSE);
        }
        /*give another good name to the original ps_function
        to build the Decoder class 
        start Decoder*/        
        void start_utt(int *errcode){
                *errcode = ps_start_utt($self);
        }
         /*really decode raw audio data
        two step: process data into feature +score and search*/
        int process_raw(const int16 *SDATA, size_t NSAMP, bool no_search,bool full_utt, int *errcode) {
                return *errcode = ps_process_raw($self, SDATA, NSAMP, no_search, full_utt);
        }
        /*after process_raw end Decoder*/
        void end_utt(int *errcode){
                *errcode = ps_end_utt($self);
        }
        
        char const *seg_threshold(int32 threshold,char const*orderfilename){
                char const *hypstr;
                hypstr = ps_seg_threshold($self,threshold,orderfilename);
                return hypstr;
        }
        int32 getscore(char const *hyp){
                return ps_seg_threshold_getscore($self,hyp);
        }
        

}
