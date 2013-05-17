<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:variable name="lcase">
		abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя
	</xsl:variable>
	<xsl:variable name="ucase">
		ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ
	</xsl:variable>

	<xsl:template match="*">
		<xsl:element name="{translate(local-name(),$ucase,$lcase)}">
			<xsl:apply-templates select="@* | node()" />
		</xsl:element>
	</xsl:template>

	<xsl:template match="@*">
		<xsl:attribute name="{translate(local-name(),$ucase,$lcase)}">
            <xsl:value-of select="." />
        </xsl:attribute>
	</xsl:template>

	<xsl:template match="comment()">
		<xsl:comment>
			<xsl:value-of select="." />
		</xsl:comment>
	</xsl:template>

	<xsl:template match="processing-instruction()">
		<xsl:copy />
	</xsl:template>

</xsl:stylesheet>