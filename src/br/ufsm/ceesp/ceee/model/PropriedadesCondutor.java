package br.ufsm.ceesp.ceee.model;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rafael
 * Date: 01/03/14
 * Time: 10:05
 * To change this template use File | Settings | File Templates.
 */
public class PropriedadesCondutor implements Serializable {

    private String nome;
    private double r1;
    private double r0;
    private double x1;
    private double x0;
    private int capacidade;

    private static Map<String, PropriedadesCondutor> propriedades = new HashMap<String, PropriedadesCondutor>();
    private static List<PropriedadesCondutor> CONDUTORES_POR_CAPACIDADE = new ArrayList<PropriedadesCondutor>();

    static {
        PropriedadesCondutor prop;

        //Importados do ASD
        prop = new PropriedadesCondutor("1#1/0CA", 0.6047, 0.9683, 0.7825, 2.4315, 203);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#1/0CAA", 0.6908, 1.0383, 0.8686, 2.5015, 220);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#1CA", 0.6047, 0.9683, 0.7825, 2.4315, 203);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#1CAA", 0.6908, 1.0383, 0.8686, 2.5015, 220);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#2CA", 0.9633, 0.9873, 1.1411, 2.4505, 152);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#2CAA", 1.0503, 1.0439, 1.2281, 2.5071, 160);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#397.5CAZ", 31.0490, 1.1640, 31.0190, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#3CAZ", 31.0490, 1.1640, 31.0190, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#477CAZ", 31.0490, 1.1640, 31.0190, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#4CA", 1.5289, 1.0160, 1.7066, 2.4792, 114);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#4CAA", 1.5973, 1.0401, 1.7750, 2.5033, 125);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#4CAZ", 31.049, 1.1640, 31.0190, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#4CC", 0.9434, 1.0091, 1.1212, 2.4723, 163);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#5CAA", 1.5973, 1.0401, 1.7750, 2.5033, 125);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#6CAA", 2.4736, 1.0488, 2.6513, 2.5121, 80);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#6CC", 1.4978, 1.0265, 1.6756, 2.4897, 121);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#6Cu", 1.4978, 1.0265, 1.6756, 2.4897, 121);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#50MM2-SP(25KV)", 0.8220, 0.3350, 1.0000, 2.4370, 247);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#8CC", 2.3617, 1.0439, 2.5395, 2.5071, 72);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#11.9CA", 31.0490, 1.1640, 31.0190, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#11.9CAA", 31.0490, 1.1640, 31.0190, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#11.9CAZ", 31.0490, 1.1640, 31.0190, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#3.09CAA", 31.0490, 1.1640, 31.0190, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#3.09CAZ", 31.0490, 1.1640, 31.0190, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#4.8mmACOHS", 13.842, 0.9880, 13.9010, 0.9880, 36);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#1/0CA", 0.6047, 0.4483, 0.7825, 1.9115, 203);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#1/0CAA", 0.6908, 0.5183, 0.8686, 1.9815, 220);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#1CA", 0.6047, 0.4483, 0.7825, 1.9115, 203);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#2CA", 0.9633, 0.4673, 1.1411, 1.9305, 152);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#2CAA", 1.0503, 0.5239, 1.2281, 1.9871, 160);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#3CAZ", 31.0490, 1.1640, 31.019, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#4CA", 1.5289, 0.4960, 1.7066, 1.9592, 114);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#4CAA", 1.5973, 0.5201, 1.7750, 1.9833, 125);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#4CC", 0.9434, 0.4891, 1.1212, 1.9523, 163);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#6CA", 2.4301, 0.5022, 2.6078, 1.9538, 80);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#6CAA", 2.4736, 0.5288, 2.6513, 1.9921, 80);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#6CC", 1.4978, 0.5065, 1.6756, 1.9697, 121);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#6Cu", 1.4978, 0.5065, 1.6756, 1.9697, 121);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#50MM2-SP(25KV)", 0.8220, 0.3350, 1.0000, 2.4370, 247);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#8CC", 2.3617, 0.5239, 2.5395, 1.9871, 72);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#11.9CAZ", 31.0490, 1.1640, 31.019, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#3.09CAZ", 31.0490, 1.1640, 31.019, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#4.8mmACOHS", 13.842, 0.9880, 13.9010, 0.9880, 36);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#1/0CA", 0.6047, 0.4483, 0.7825, 1.9115, 203);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#1/0CAA", 0.6908, 0.5183, 0.8686, 1.9815, 220);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#1/0CC", 0.3773, 0.4499, 0.5550, 1.9131, 305);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#1CA", 0.6047, 0.4483, 0.7825, 1.9115, 203);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#1CAA", 0.6908, 0.5183, 0.8686, 1.9815, 220);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#1CC", 0.3773, 0.4499, 0.5550, 1.9131, 305);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#2/0CA", 0.4792, 0.4410, 0.6569, 1.9042, 235);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#2/0CAA", 0.5563, 0.5090, 0.7340, 1.9722, 250);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#2CA", 0.9633, 0.4673, 1.1411, 1.9305, 152);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#2CAA", 1.0503, 0.5239, 1.2281, 1.9871, 160);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#2CC", 0.5991, 0.4717, 0.7769, 1.9349, 226);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#3/0CA", 0.3810, 0.4323, 0.5587, 1.8955, 271);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#3/0CAA", 0.4494, 0.4965, 0.6271, 1.9597, 290);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#3/0CC", 0.2374, 0.4325, 0.4152, 1.8957, 412);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#336.4", 0.1908, 0.4007, 0.3686, 1.8640, 419);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#336.4CA", 0.1908, 0.4007, 0.3686, 1.8640, 419);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#336.4CAA", 0.1902, 0.3871, 0.3679, 1.8503, 428);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#336.4CC", 0.1908, 0.4007, 0.3686, 1.8640, 419);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#397.5CAA", 0.1902, 0.3871, 0.3679, 1.8503, 428);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#3CAA", 0.4494, 0.4965, 0.6271, 1.9597, 290);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4/0CA", 0.3021, 0.4236, 0.4798, 1.8868, 314);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4/0CAA", 0.3679, 0.4717, 0.5457, 1.9349, 330);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4/0CC", 0.1883, 0.4232, 0.3661, 1.8864, 477);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4/0SUCA", 0.3472, 0.1367, 2.4440, 0.0730, 537);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#477CA", 0.1342, 0.3888, 0.3120, 1.8520, 519);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#477CAA", 0.1342, 0.3778, 0.3120, 1.8410, 523);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4CA", 1.5289, 0.4960, 1.7066, 1.9592, 114);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4CAA", 1.5973, 0.5201, 1.7750, 1.9833, 125);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4CC", 0.9434, 0.4891, 1.1212, 1.9523, 163);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#6CA", 2.4301, 0.5022, 2.6078, 1.9538, 80);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#6CAA", 2.4736, 0.5288, 2.6513, 1.9921, 80);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#6CC", 1.4978, 0.5065, 1.6756, 1.9697, 121);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#6Cu", 1.4978, 0.5065, 1.6756, 1.9697, 121);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#50MM2-SP(25KV)", 0.8220, 0.3350, 1.0000, 2.4370, 247);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#7CC", 2.3617, 0.5239, 2.5395, 1.9871, 72);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#8CC", 2.3617, 0.5239, 2.5395, 1.9871, 72);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#11.9CAA", 31.049, 1.1640, 31.0190, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#11.9CAZ", 31.049, 1.1640, 31.0190, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#3.09CAZ", 31.049, 1.1640, 31.0190, 1.5890, 6);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4.8mmACOHS", 13.842, 0.9880, 13.9010, 0.9880, 36);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#35CA", 0.8680, 0, 0, 0, 149);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#2/0CAA", 0.5563, 0.5090, 0.7340, 1.9722, 250);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#336.4CAA", 0.1902, 0.3871, 0.3679, 1.8503, 428);
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#92CAA", 0.1902, 0.3871, 0.3679, 1.8503, 428);
        propriedades.put(prop.nome, prop);
        //chute
        prop = new PropriedadesCondutor("1#11.93CAZ", 31.0490, 1.1640, 31.0190, 1.5890, 6);
        propriedades.put(prop.nome, prop);

        //AES IMPORTADOS DO ASE.
        prop = new PropriedadesCondutor("ZERO", "0.01", "0.01", "0.01", "0.01", "9999.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#300CA", "0.1908", "0.4007", "0.3686", "1.864", "418.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#250CC", "0.1908", "0.4007", "0.3686", "1.864", "418.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#35XLPE", "1.5289", "0.496", "1.7066", "1.9592", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#50XLPE", "1.5289", "0.496", "1.7066", "1.9592", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#50XLPE", "1.5289", "0.496", "1.7066", "1.9592", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#1/0CA", "0.6047", "0.4483", "0.7825", "1.9115", "202.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#1/0CA", "0.6047", "0.4483", "0.7825", "1.9115", "202.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#1/0CA", "0.6047", "0.4483", "0.7825", "1.9115", "202.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#1/0CAA", "0.6908", "0.5183", "0.8686", "1.9815", "198.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#1/0CAA", "0.6908", "0.5183", "0.8686", "1.9815", "198.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#1/0CAA", "0.6908", "0.5183", "0.8686", "1.9815", "198.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#1/0CAA", "0.6908", "0.5183", "0.8686", "1.9815", "198.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("5#1/0CAA", "0.6908", "0.5183", "0.8686", "1.9815", "198.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("6#1/0CAA", "0.6908", "0.5183", "0.8686", "1.9815", "198.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("7#1/0CAA", "0.6908", "0.5183", "0.8686", "1.9815", "198.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#1/0CC", "0.3773", "0.4499", "0.555", "1.9131", "305.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#1/0CC", "0.3773", "0.4499", "0.555", "1.9131", "305.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#1/0CC", "0.3773", "0.4499", "0.555", "1.9131", "305.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#1CA", "0.76", "0.45", "0.94", "1.92", "167.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#1CA", "0.76", "0.45", "0.94", "1.92", "167.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#1CA", "0.76", "0.45", "0.94", "1.92", "167.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#1CAA", "0.85", "0.52", "1.03", "1.98", "160.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#1CAA", "0.85", "0.52", "1.03", "1.98", "160.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#1CC", "0.47", "0.45", "0.65", "1.91", "200.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#132", "0.25", "0.31", "1.38", "1.5", "300.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#150CA_COMPACT15", "0.2081", "0.2157", "0.3857", "1.8689", "450.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#150CA_COMPACT35", "0.2081", "0.2498", "0.3857", "1.8007", "450.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#2/0CA", "0.4792", "0.961", "0.6569", "2.4242", "235.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#2/0CA", "0.4792", "0.441", "0.6569", "1.9042", "235.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#2/0CA", "0.4792", "0.441", "0.6569", "1.9042", "235.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("5#2/0CA", "0.4792", "0.441", "0.6569", "1.9042", "235.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("6#2/0CA", "0.4792", "0.441", "0.6569", "1.9042", "235.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("7#2/0CA", "0.4792", "0.441", "0.6569", "1.9042", "235.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#2/0CAA", "0.5563", "0.509", "0.734", "1.9722", "250.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#2/0CAA", "0.5563", "0.509", "0.734", "1.9722", "250.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#2/0CAA", "0.5563", "0.509", "0.734", "1.9722", "250.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#2/0CAA", "0.5563", "0.509", "0.734", "1.9722", "250.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#2/0CC", "0.2989", "0.9612", "0.4767", "2.4244", "354.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#2/0CC", "0.2989", "0.4412", "0.4767", "1.9044", "354.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#2/0CC", "0.2989", "0.4412", "0.4767", "1.9044", "354.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#2/0SUCC", "0.3293", "0.1237", "1.7561", "0.186", "555.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#2CA", "0.9633", "0.4673", "1.1411", "1.9305", "152.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#2CA", "0.9633", "0.4673", "1.1411", "1.9305", "152.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#2CA", "0.9633", "0.4673", "1.1411", "1.9305", "152.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("5#2CA", "0.9633", "0.4673", "1.1411", "1.9305", "152.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("6#2CA", "0.9633", "0.4673", "1.1411", "1.9305", "152.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("7#2CA", "0.9633", "0.4673", "1.1411", "1.9305", "152.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#2CAA", "1.0503", "0.5239", "1.2281", "1.9871", "150.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#2CAA", "1.0503", "0.5239", "1.2281", "1.9871", "150.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#2CAA", "1.0503", "0.5239", "1.2281", "1.9871", "150.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#2CAA", "1.0503", "0.5239", "1.2281", "1.9871", "150.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("5#2CAA", "1.0503", "0.5239", "1.2281", "1.9871", "150.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("6#2CAA", "1.0503", "0.5239", "1.2281", "1.9871", "150.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#2CC", "0.5991", "0.4717", "0.7769", "1.9349", "226.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#2CC", "0.5991", "0.4717", "0.7769", "1.9349", "226.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#3/0CA", "0.381", "0.4323", "0.5587", "1.8955", "271.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#3/0CA", "0.381", "0.4323", "0.5587", "1.8955", "271.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#3/0CAA", "0.4494", "0.4965", "0.6271", "1.9597", "290.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#3/0CAA", "0.4494", "0.4965", "0.6271", "1.9597", "290.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#3/0CAA", "0.4494", "0.4965", "0.6271", "1.9597", "290.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#3/0CC", "0.2374", "0.4325", "0.4152", "1.8957", "412.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#3/0CC", "0.2374", "0.4325", "0.4152", "1.8957", "412.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#3/0CC", "0.2374", "0.4325", "0.4152", "1.8957", "412.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#336.4CA", "0.1908", "0.4007", "0.3686", "1.864", "418.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#336.4CA", "0.1908", "0.4007", "0.3686", "1.864", "418.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#336.4CA", "0.1908", "0.4007", "0.3686", "1.864", "418.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#336.4CA", "0.1908", "0.4007", "0.3686", "1.864", "418.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#336.4CAA", "0.1902", "0.3871", "0.3679", "1.8503", "428.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#336.4CAA", "0.1902", "0.3871", "0.3679", "1.8503", "428.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#336.4CAA", "0.1902", "0.3871", "0.3679", "1.8503", "428.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#3CAA", "1.59", "1.04", "1.775", "2.5", "125.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#3CAA", "1.59", "1.04", "1.775", "2.5", "125.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#3CAA", "1.59", "1.04", "1.775", "2.5", "125.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#3CAA", "1.59", "1.04", "1.775", "2.5", "125.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#3CAZ", "19.2", "0.65", "19.384", "1.07", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#4/0CA", "0.3021", "0.4236", "0.4798", "0.8868", "313.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#4/0CA", "0.3021", "0.4236", "0.4798", "0.8868", "313.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#4/0CA", "0.3021", "0.4236", "0.4798", "0.8868", "313.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#4/0CAA", "0.3679", "0.4717", "0.5457", "1.9349", "295.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#4/0CAA", "0.3679", "0.4717", "0.5457", "1.9349", "295.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4/0CAA", "0.3679", "0.4717", "0.5457", "1.9349", "295.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#4/0CAA", "0.3679", "0.4717", "0.5457", "1.9349", "295.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#4/0CC", "0.1883", "0.4232", "0.3661", "1.8864", "477.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#4/0SUCA", "0.3472", "0.1367", "2.444", "0.073", "537.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#4/0SUCA", "0.34", "0.13", "2.44", "0.07", "537.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4/0SUCC", "0.2078", "0.1149", "1.4822", "0.1764", "744.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#42", "0.35", "0.32", "1.45", "1.5", "300.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#477CA", "0.1342", "0.3888", "0.312", "1.852", "518.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#4CA", "1.5289", "0.496", "1.7066", "1.9592", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#4CA", "1.5289", "0.496", "1.7066", "1.9592", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#4CA", "1.5289", "0.496", "1.7066", "1.9592", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("5#4CA", "1.5289", "0.496", "1.7066", "1.9592", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("6#4CA", "1.5289", "0.496", "1.7066", "1.9592", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("7#4CA", "1.5289", "0.496", "1.7066", "1.9592", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#4CAA", "1.5973", "0.5201", "1.775", "1.9833", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4CAA", "1.5973", "0.5201", "1.775", "1.9833", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#4CAA", "1.5973", "0.5201", "1.775", "1.9833", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("5#4CAA", "1.5973", "0.5201", "1.775", "1.9833", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("6#4CAA", "1.5973", "0.5201", "1.775", "1.9833", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("7#4CAA", "1.5973", "0.5201", "1.775", "1.9833", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#4CC", "0.9434", "0.4891", "1.1212", "1.9523", "163.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#4CC", "0.9434", "0.4891", "1.1212", "1.9523", "163.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4CC", "0.9434", "0.4891", "1.1212", "1.9523", "163.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#4CC", "0.9434", "0.4891", "1.1212", "1.9523", "163.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#50CA_COMPACT15", "0.6417", "0.2417", "0.8193", "1.8949", "224.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#50CA_COMPACT35", "0.6417", "0.2758", "0.8193", "1.8266", "224.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#5CAA", "1.783", "0.504", "2.15", "2.5", "140.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#6CA", "2.4301", "0.5022", "2.6078", "1.9538", "80.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#6CA", "2.4301", "0.5022", "2.6078", "1.9538", "80.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#6CA", "2.4301", "0.5022", "2.6078", "1.9538", "80.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#6CAA", "2.4736", "0.5288", "2.6513", "1.9921", "80.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#6CAA", "2.4736", "0.5288", "2.6513", "1.9921", "80.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#6CAA", "2.4736", "0.5288", "2.6513", "1.9921", "80.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#6CAA", "2.4736", "0.5288", "2.6513", "1.9921", "80.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("5#6CAA", "2.4736", "0.5288", "2.6513", "1.9921", "80.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#6CC", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#6CC", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("5#6CC", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("6#6CC", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("7#6CC", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("8#6CC", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("9#6CC", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("0#6CC1", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#6CC1", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#6CC1", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#6CC1", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#6CC1", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("5#6CC1", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#7CC", "2.3617", "1.0439", "2.5395", "2.507", "80.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#8CC", "2.3617", "0.5239", "2.5395", "1.9871", "72.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#8CC", "2.3617", "0.5239", "2.5395", "1.9871", "72.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#8CC", "2.3617", "0.5239", "2.5395", "1.9871", "72.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("5#8CC", "2.3617", "0.5239", "2.5395", "1.9871", "72.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("6#8CC", "2.3617", "0.5239", "2.5395", "1.9871", "72.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#95CA_COMPACT15", "0.3216", "0.2263", "0.4992", "1.8795", "342.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#95CA_COMPACT35", "0.3216", "0.2604", "0.4992", "1.8113", "342.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#C11.9CA", "19.207", "0.644", "19.384", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#C11.9CAA", "19.207", "0.644", "19.384", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#C11.9CAA", "19.207", "0.644", "19.384", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#C11.9CAA", "19.207", "0.644", "19.384", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#C11.9CAZ", "19.207", "0.644", "19.384", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#C11.9CAZ", "19.207", "0.644", "19.384", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#C11.9CAZ", "19.207", "0.644", "19.384", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#C11.9CAZ", "19.207", "0.644", "19.384", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("5#C11.9CAZ", "19.207", "0.644", "19.384", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#C2.25CAZ", "19.207", "1.1644", "19.384", "1.589", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#C2.25CAZ", "19.207", "0.644", "19.384", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#C2.25CAZ", "19.207", "0.644", "19.384", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#F3.09CAA", "31.049", "0.644", "31.019", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#F3.09CAA", "31.049", "0.644", "31.019", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#F3.09CAA", "31.049", "0.644", "31.019", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#F3.09CAZ", "31.049", "0.644", "31.019", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#F3.09CAZ", "31.049", "0.644", "31.019", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#F3.09CAZ", "31.049", "0.644", "31.019", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("4#F3.09CAZ", "31.049", "0.644", "31.019", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("5#F3.09CAZ", "31.049", "0.644", "31.019", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#LT44kV", "0.4398", "0.5465", "0.9966", "2.1334", "220.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#LT69conc", "0.3017", "0.4952", "0.6231", "1.3154", "600.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#LT69metl", "0.1348", "0.4957", "0.4609", "1.5483", "600.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#120CA_COMPACT15", "0.2543", "0.221", "0.4317", "1.8742", "379.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#120CA_COMPACT35", "0.2543", "0.2551", "0.4319", "1.806", "375.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#185CA_COMPACT15", "0.1656", "0.208", "0.3432", "1.8612", "525.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#185CA_COMPACT35", "0.1656", "0.2422", "0.3432", "1.793", "525.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#1CAA", "0.85", "0.52", "1.03", "1.98", "160.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#1CC", "0.47", "0.45", "0.65", "1.91", "200.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#1CC", "0.47", "0.45", "0.65", "1.91", "200.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#2/0SUCC", "0.3293", "0.1237", "1.7561", "0.186", "555.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#2/0SUCC", "0.3293", "0.1237", "1.7561", "0.186", "555.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#3CAZ", "19.2", "0.65", "19.384", "1.07", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#3CAZ", "19.2", "0.65", "19.384", "1.07", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#4/0CC", "0.1883", "0.4232", "0.3661", "1.8864", "477.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4/0CC", "0.1883", "0.4232", "0.3661", "1.8864", "477.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#477CA", "0.1342", "0.3888", "0.312", "1.852", "519.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#477CA", "0.1342", "0.3888", "0.312", "1.852", "518.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#5CAA", "1.783", "0.504", "2.15", "2.5", "140.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#5CAA", "1.783", "0.504", "2.15", "2.5", "140.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#C11.9CA", "19.207", "0.644", "19.384", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#C11.9CA", "19.207", "0.644", "19.384", "1.069", "6.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#750CA", "0.3679", "1.8503", "0.1902", "0.3871", "428.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#150CACP", "0.2081", "0.2157", "0.3857", "1.8689", "450.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#170CACP", "0.2081", "0.2498", "0.3857", "1.8007", "450.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#70CACP", "0.6417", "0.2758", "0.8193", "1.8266", "224.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#95CACP", "0.3216", "0.2263", "0.4992", "1.8795", "342.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#120CACP", "0.2543", "0.221", "0.4317", "1.8742", "379.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#185CACP", "0.1656", "0.208", "0.3432", "1.8612", "525.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#240CACP", "0.1908", "0.4007", "0.3686", "1.864", "418.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4/0CACP", "0.3021", "0.4236", "0.4798", "0.8868", "313.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#336.4CACP", "0.1908", "0.4007", "0.3686", "1.864", "418.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#397.5CAA", "0.1902", "0.3871", "0.3679", "1.8503", "428.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#16XLPE", "0.01", "0.01", "0.01", "0.01", "300.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#16PE", "0.01", "0.01", "0.01", "0.01", "300.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#1/0CA", "0.6047", "0.4483", "0.7825", "1.9115", "202.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#2/0CA", "0.4792", "0.441", "0.6569", "1.9042", "235.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#2CA", "0.9633", "0.4673", "1.1411", "1.9305", "152.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#2CC", "0.5991", "0.4717", "0.7769", "1.9349", "226.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4/0CA", "0.3021", "0.4236", "0.4798", "0.8868", "313.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#4CA", "1.5289", "0.496", "1.7066", "1.9592", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#4CAA", "1.5973", "0.5201", "1.775", "1.9833", "114.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#6CC", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#6CC", "1.4978", "0.5065", "1.6756", "1.9697", "121.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#8CC", "2.3617", "0.5239", "2.5395", "1.9871", "72.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#3/0CA", "0.38", "0.4323", "0.5587", "1.8955", "271.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#50CACP", "0.6417", "0.2417", "0.8193", "1.8949", "224.0");
        propriedades.put(prop.nome, prop);

        /*
        prop = new PropriedadesCondutor("3#35XLPE", "0.01", "0.01", "0.01", "0.01", "100.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#16XLPE", "0.01", "0.01", "0.01", "0.01", "100.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#70XLPE", "0.01", "0.01", "0.01", "0.01", "100.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#120XLPE", "0.01", "0.01", "0.01", "0.01", "100.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#16PE", "0.01", "0.01", "0.01", "0.01", "300.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#16XLPE", "0.01", "0.01", "0.01", "0.01", "200.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#5NAS", "0.01", "0.01", "0.01", "0.01", "200.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#5CBAL", "0.01", "0.01", "0.01", "0.01", "200.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#2/0CACP", "0.01", "0.01", "0.01", "0.01", "200.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#150XLPE", "0.01", "0.01", "0.01", "0.01", "200.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#185XLPE", "0.01", "0.01", "0.01", "0.01", "200.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#95XLPE", "0.01", "0.01", "0.01", "0.01", "200.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#70XLPE", "0.01", "0.01", "0.01", "0.01", "200.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("3#35CACP", "0.01", "0.01", "0.01", "0.01", "200.0");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("2#70XLPE", "0.0", "0.0", "0.0", "0.0", "192");
        propriedades.put(prop.nome, prop);
        prop = new PropriedadesCondutor("1#70CACP", "0.0", "0.0", "0.0", "0.0", "0");
        propriedades.put(prop.nome, prop);*/

        prop = new PropriedadesCondutor("", "0.0", "0.0", "0.0", "0.0", "999999999999");
        propriedades.put(prop.nome, prop);

        //xlsx do DANIEL!
        prop = new PropriedadesCondutor("3#185CACP", "0.1656", "0.2081", "0.3432", "1.8613", "500");
        propriedades.put(prop.nome, prop);

        //COPIADO DO CONDUTOR 3#1/0CAA DO xlsx do DANIEL!
        prop = new PropriedadesCondutor("3#1/0CA", "0,6910", "0.5180", "0.8690", "0.9810", "195");
        propriedades.put(prop.nome, prop);

        //novos condutores da tabela do interplan

        prop = new PropriedadesCondutor("3#70XLPE", "0.44433", "0.23405", "0.62193", "1.88725", "192");
        propriedades.put(prop.nome, prop);

        prop = new PropriedadesCondutor("3#35XLPE", "0.86887", "0.24757", "1.04647", "0.86887", "129");
        propriedades.put(prop.nome, prop);

        prop = new PropriedadesCondutor("3#16XLPE", "0.32160", "0.22630", "0.49920", "1.87949", "76");
        propriedades.put(prop.nome, prop);

        prop = new PropriedadesCondutor("3#95XLPE", "0.32160", "0.22630", "0.49920", "1.87949", "255");
        propriedades.put(prop.nome, prop);

        prop = new PropriedadesCondutor("3#95mmXLPE", "0.32160", "0.22630", "0.49920", "1.87949", "255");
        propriedades.put(prop.nome, prop);

        prop = new PropriedadesCondutor("3#150XLPE", "0.20806", "0.21570", "0.38566", "1.86890", "450");
        propriedades.put(prop.nome, prop);

        prop = new PropriedadesCondutor("3#150mmXLPE", "0.20806", "0.21570", "0.38566", "1.86890", "450");
        propriedades.put(prop.nome, prop);

        prop = new PropriedadesCondutor("3#185XLPE", "0.16564", "0.20805", "0.34324", "1.86125", "500");
        propriedades.put(prop.nome, prop);

        prop = new PropriedadesCondutor("3#185mmXLPE", "0.16564", "0.20805", "0.34324", "1.86125", "500");
        propriedades.put(prop.nome, prop);

        CONDUTORES_POR_CAPACIDADE.addAll(propriedades.values());
        Collections.sort(CONDUTORES_POR_CAPACIDADE, new Comparator<PropriedadesCondutor>() {
            @Override
            public int compare(PropriedadesCondutor o1, PropriedadesCondutor o2) {
                return o1.getCapacidade() - o2.getCapacidade();
            }
        });
    }

    public static PropriedadesCondutor findCapacidadeMaiorIgual(int numFases, int capacidade) {
        for (PropriedadesCondutor prop : CONDUTORES_POR_CAPACIDADE) {
            if (prop.getNome().startsWith(numFases + "#") && prop.getCapacidade() >= capacidade) {
                return prop;
            }
        }
        return null;
    }

    public static PropriedadesCondutor getPropriedadesCondutor(String condutor) {
        return propriedades.get(condutor);
    }

    public PropriedadesCondutor(String nome, double r1, double x1, double r0, double x0, int capacidade) {
        this.nome = nome;
        this.r1 = r1;
        this.r0 = r0;
        this.x1 = x1;
        this.x0 = x0;
        this.capacidade = capacidade;
    }

    public PropriedadesCondutor(String nome, String r1, String x1, String r0, String x0, String capacidade) {
        this.nome = nome;
        try {
            r1 = r1.replaceFirst(",", ".").trim();
            r0 = r0.replaceFirst(",", ".").trim();
            x1 = x1.replaceFirst(",", ".").trim();
            x0 = x0.replaceFirst(",", ".").trim();
            this.r1 = Double.parseDouble(r1);
            this.r0 = Double.parseDouble(r0);
            this.x1 = Double.parseDouble(x1);
            this.x0 = Double.parseDouble(x0);
            try {
                this.capacidade = new Double(capacidade).intValue();
            } catch (Exception e) {
                this.capacidade = 0;
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getR1() {
        return r1;
    }

    public void setR1(double r1) {
        this.r1 = r1;
    }

    public double getR0() {
        return r0;
    }

    public void setR0(double r0) {
        this.r0 = r0;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX0() {
        return x0;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public static Collection<String> getNomePropriedades() {
        return propriedades.keySet();
    }
}
