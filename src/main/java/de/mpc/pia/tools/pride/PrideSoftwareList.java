package de.mpc.pia.tools.pride;

import de.mpc.pia.tools.MzIdentMLTools;
import uk.ac.ebi.jmzidml.model.mzidml.AbstractParam;
import uk.ac.ebi.jmzidml.model.mzidml.AnalysisSoftware;
import uk.ac.ebi.jmzidml.model.mzidml.CvParam;
import uk.ac.ebi.jmzidml.model.mzidml.Param;

/**
 * This enum holds the PRIDE software tags and its conversions into PSI-OBO
 * tags.
 *
 * @author julian
 *
 */
public enum PrideSoftwareList {

    MATRIX_SCIENCE_MASCOT("mascot", "Matrix Science Mascot", "Mascot", "MS:1001207", "http://www.matrixscience.com/"),

    ;

    /** tha NAME, that will be put into the software element of mzIdentML */
    private String shortName;

    /** the NAME in the PRIDE XML format */
    private String prideName;

    /** NAME in PSI OBO*/
    private String psiName;

    /** accession in PSI OBO */
    private String psiAccession;

    /** URI to the software*/
    private String uri;


    /**
     * basic constructor
     *
     * @param shortName short description, used for the software element NAME in
     * mzIdentML
     * @param prideName NAME in PRIDE XML files, must not be null
     * @param psiName NAME in PSI OBO, must not be null
     * @param psiAccession accession in PSI OBO, must not be null
     * @param uri URI to the software, may be null
     */
    PrideSoftwareList(String shortName, String prideName, String psiName,
            String psiAccession, String uri) {
        this.shortName = shortName;
        this.prideName = prideName;
        this.psiName = psiName;
        this.psiAccession = psiAccession;
        this.uri = uri;
    }


    /**
     * Returns the PRIDE software given by the NAME. If none exists, returns
     * null.
     *
     * @param prideName
     * @return
     */
    public static PrideSoftwareList getByPrideName(String prideName) {
        for (PrideSoftwareList software : values()) {
            if (software.prideName.equals(prideName)) {
                return software;
            }
        }

        return null;
    }


    /**
     * Creates an {@link AnalysisSoftware} representation of the PRIDE XML
     * software.
     *
     * @return
     */
    public AnalysisSoftware getAnalysisSoftwareRepresentation() {
        AnalysisSoftware software = new AnalysisSoftware();

        software.setId(shortName);
        software.setName(shortName);

        if (uri != null) {
            software.setUri(uri);
        }

        AbstractParam abstractParam;
        Param param = new Param();
        abstractParam = new CvParam();
        ((CvParam)abstractParam).setAccession(psiAccession);
        ((CvParam)abstractParam).setCv(MzIdentMLTools.getCvPSIMS());
        abstractParam.setName(psiName);
        param.setParam(abstractParam);

        software.setSoftwareName(param);

        return software;
    }
}
