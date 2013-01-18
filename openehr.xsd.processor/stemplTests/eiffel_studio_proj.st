<?xml version="1.0" encoding="ISO-8859-1"?>
<system xmlns="http://www.eiffel.com/developers/xml/configuration-1-6-0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.eiffel.com/developers/xml/configuration-1-6-0 http://www.eiffel.com/developers/xml/configuration-1-6-0.xsd" name="$target_name$" uuid="126FC5C9-C672-4F68-842B-92ABF7693B8E" readonly="false" library_target="$target_name$">
	<target name="$target_name$">
		<root class="MAIN_PB_WRAPPER" feature="make"/>
		<option warning="true">
			<assertions precondition="true" postcondition="true" check="true" invariant="true" loop="true" supplier_precondition="true"/>
		</option>
		<external_include location="%wrapperlibs%\\ProtocolBuffersHeaders"/>
		<external_include location="%wrapperlibs%\\include"/>
		<external_library location="%wrapperlibs%\\libprotobuf.lib"/>
		<external_library location="%wrapperlibs%\\ProtocolBuffersStubs.lib"/>
		
		<library name="base" location="\$ISE_LIBRARY\\library\\base\\base.ecf"/>
		<cluster name="$target_name$" location=".\" recursive="true">
			<file_rule>
				<exclude>/EIFGENs\$</exclude>
				<exclude>/CVS\$</exclude>
				<exclude>/.svn\$</exclude>
			</file_rule>
		</cluster>
	</target>
	<target name="protocol_buffer_wrapper_dotnet" extends="$target_name$">
		<setting name="msil_generation" value="true"/>
	</target>
</system>
