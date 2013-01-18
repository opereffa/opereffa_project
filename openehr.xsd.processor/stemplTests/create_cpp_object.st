create_pb_object:POINTER
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
	  $pb_message_type_name$* pbObj  = new $pb_message_type_name$();		  			
	  return pbObj;
	]"

end