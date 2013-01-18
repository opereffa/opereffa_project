cpp_deserialize_pb_object(p_byte_arr:POINTER; p_size:INTEGER):POINTER
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
		$pb_parent_type$* pbObj = new $pb_parent_type$();			
		const int size = \$p_size;
		byte* arr = (byte*)\$p_byte_arr;
		pbObj->ParseFromArray(arr,size);
		return pbObj;
	]"
end