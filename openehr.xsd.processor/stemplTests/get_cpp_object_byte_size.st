get_pb_byte_size(pb_object:POINTER):INTEGER
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
	  $pb_object_type$* pbObj  = ($pb_object_type$*)\$pb_object;
	  return pbObj->ByteSize();

	]"

end