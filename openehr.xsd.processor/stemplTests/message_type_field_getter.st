get_$field_name$:$field_type$	
	do
		if $field_name$=void then
			create $field_name$.make
			$field_name$.set_protoc_ptr (get_$field_name$_from_pb_object(get_protoc_ptr))
		end
		Result := $field_name$
	end