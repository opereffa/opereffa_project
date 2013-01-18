deserialize_pb_object_from_string(p_string:STRING):BOOLEAN
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
		$pb_parent_type$* pbObj = new $pb_parent_type$();			
		std::string str((char*)\$p_string);					
		return (pbObj->ParseFromString(str));
	]"
end