cpp_serialize_pb_object(p_byte_arr:POINTER; pb_object:POINTER; p_size:INTEGER)
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
	  $pb_object_type$* pbObj  = ($pb_object_type$*)\$pb_object;
	  const int size = \$p_size;
	  byte* arr = (byte*)\$p_byte_arr;
	  pbObj->SerializeToArray(arr,size);
	  
	]"

end
