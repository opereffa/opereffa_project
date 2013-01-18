note
	description: "Summary description for {PB_OBJECT_WRAPPER}."
	author: ""
	date: "$Date$"
	revision: "$Revision$"

deferred  class
	PB_OBJECT_WRAPPER


feature
	is_root_object: BOOLEAN

	set_is_root_object(p_is_root:BOOLEAN)
	do
		is_root_object := p_is_root
	end
	
	deserialize_from_byte_arr(p_byte_arr:MANAGED_POINTER)
	require
		pb_not_null: p_byte_arr.item /= default_pointer
	do
		protobuf_object := cpp_deserialize_pb_object(p_byte_arr.item, p_byte_arr.count)
		--only top level objects are deserialized from byte array
		is_root_object := true
	end
	
	cpp_serialize_pb_object(p_byte_arr:POINTER; pb_object:POINTER; p_size:INTEGER)
	deferred
	end
	
	cpp_serialize_pb_object_to_string(pb_object:POINTER):STRING
	deferred
	end

	cpp_deserialize_pb_object(p_byte_arr:POINTER; p_size:INTEGER):POINTER
	deferred
	end
	
	serialize_pb_object_to_string:STRING
	do
		Result := cpp_serialize_pb_object_to_string (get_protoc_ptr)
	end
	
	deserialize_pb_object_from_string(p_string:STRING):BOOLEAN
	deferred
	end

	get_pb_object_size:INTEGER
	deferred
	end

	serialize_to_file(p_path:STRING)
	local
		file:RAW_FILE
		file_contents:MANAGED_POINTER
	do
		create file.make_open_write (p_path)
		create file_contents.make (get_pb_object_size)
		cpp_serialize_pb_object (file_contents.item, get_protoc_ptr, get_pb_object_size )
		file.put_managed_pointer (file_contents, 0, file_contents.count)
		file.close
	end
	
	serialize_to_byte_array:POINTER
	local
		ptr:POINTER
	do
		ptr := allocate_byte_array(get_pb_object_size)
		cpp_serialize_pb_object (ptr, get_protoc_ptr, get_pb_object_size)
		Result := ptr
	end

feature --C++ object wrapper

	protobuf_object: POINTER

	init_pb_obj
	do
		set_protoc_ptr(create_pb_object)
	end

	test_pb_obj
	require
		pb_not_null: protobuf_object /= default_pointer
		deferred
		end


	set_protoc_ptr(ptr:POINTER)
	do
		protobuf_object := ptr
	end

	get_protoc_ptr:POINTER
	do
		Result := protobuf_object
	end

	create_pb_object:POINTER
	deferred
	end
	
	allocate_byte_array(p_size:INTEGER):POINTER
	local
		ptr_to_arr:POINTER
	do

		ptr_to_arr := allocate_byte_arr_cpp (p_size)		
		Result := ptr_to_arr
	end

	allocate_byte_arr_cpp(p_size:INTEGER):POINTER
	external
		"C++ inline"
	alias "[
			const int size = \$p_size;
			byte* arr = new byte[size];
			return (void*) arr;
		]"
	end

end
