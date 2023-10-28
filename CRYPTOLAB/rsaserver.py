import socket

def gcd(a, b):
    while b:
        a, b = b, a % b
    return a

def mod_inverse(e, t):
    d = 0
    while True:
        if (d * e) % t == 1:
            return d
        d += 1

def RSA_decrypt(ct, d, n):
    mes = (ct ** d) % n
    return mes

if __name__ == "__main__":
    # Create a socket and bind it to a specific address and port
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_address = ('localhost', 12345)  # You can change the address and port as needed
    server_socket.bind(server_address)

    # Listen for incoming connections
    server_socket.listen(1)
    print("Server is listening for incoming connections...")

    # Accept a connection from a client
    client_socket, client_address = server_socket.accept()
    print(f"Accepted connection from {client_address}")

    # Receive the message containing n, e, encrypted message, p, and q from the client
    message_received = client_socket.recv(4096).decode()
    n, e, ct_str, p, q = message_received.split(',')  # Split the message

    n = int(n)
    e = int(e)
    p = int(p)
    q = int(q)
    ct = list(map(int, ct_str.split(':')))  # Convert the encrypted message to a list of integers

    print(f"Received n: {n}")
    print(f"Received e: {e}")
    print(f"Received ct: {ct}")
    print(f"Received p: {p}")
    print(f"Received q: {q}")

    # Calculate the private key (d)
    t = (p - 1) * (q - 1)
    d = mod_inverse(e, t)

    decrypted_message = [RSA_decrypt(char, d, n) for char in ct]

    # Convert numbers back to alphabets
    decrypted_message = ''.join([chr((char % 26) + ord('A')) for char in decrypted_message])

    print(f"Decrypted message is: {decrypted_message}")

    # Close the sockets
    client_socket.close()
    server_socket.close()
